package main

import (
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
)

type charityDonation struct {
}

const (
	USER_DONATE    = "UserDonate_"
	ALL_DONATE     = "AllDonate_"
	ALL_COST       = "AllCost_"
	TOTAL_RECEIVED = "TotalDonates"
	TOTAL_COSTS    = "TotalCosts"
	BALANCE        = "Balance"
	DONATE_COUNT   = "DonateCount"
	COST_COUNT     = "CostCount"
	ADMIN          = "admin"
	MAX_LIMIT      = 100
)

var (
	ErrLimitExceeded = errors.New("limit exceeded")
)

func (cd *charityDonation) Initialize(ctx code.Context) code.Response {
	admin, err := ctx.GetObject(utils.Concat(ADMIN))
	if err != nil { // 这里的err 什么情况，不存在会是err么？
		return code.Error(err)
	}
	//TODO using batch put
	if err := ctx.PutObject(utils.Concat(ADMIN), admin); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.Concat(TOTAL_RECEIVED), utils.Concat("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.Concat(TOTAL_COSTS), utils.Concat("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.Concat(BALANCE), utils.Concat("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.Concat(DONATE_COUNT), utils.Concat("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.Concat(COST_COUNT), utils.Concat("0")); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (cd *charityDonation) Donate(ctx code.Context) code.Response {
	err := cd.checkPermission(ctx)
	if err != nil {
		return code.Error(err)
	}
	args := struct {
		Donor     []byte `json:"donor",required:"true"`
		Amount    []byte `json:"amount",required:"true"`
		Timestamp []byte `json:"timestamp",required:"true"`
		Comments  []byte `json:"comments",required:"comment"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	in := map[string][]byte{
		DONATE_COUNT:   nil,
		TOTAL_RECEIVED: nil,
		BALANCE:        nil,
	}
	//好黑科技,可以用assetnoerror 函数
	if err := cd.batchGet(ctx, &in); err != nil {
		return code.Error(err)
	}
	totalReceived := utils.Add(in[TOTAL_RECEIVED], args.Amount)
	balance := utils.Add(in[BALANCE], args.Amount)
	donate := string(utils.Sub(in[DONATE_COUNT], "1"))
	donateID := 0 // TODO cpp的方案是填充0到20位
	userDonateKey := string(utils.Concat(USER_DONATE, args.Donor, "%", donateID))
	allDonateKey := string(utils.Concat(ALL_DONATE, donateID))
	donateDetail := utils.Concat("donor=", args.Donor, ",amount=", args.Amount,
		",timestamp=", args.Timestamp, ",comments=", args.Comments)
	batch := map[string][]byte{
		userDonateKey:  donateDetail,
		allDonateKey:   donateDetail,
		DONATE_COUNT:   in[DONATE_COUNT],
		TOTAL_RECEIVED: totalReceived,
		BALANCE:        balance,
	}
	if err := cd.batchPut(ctx, batch); err != nil {
		return code.Error(err)
	}
	return code.OK(utils.Concat(donateID))

}

func (cd *charityDonation) Cost(ctx code.Context) code.Response {
	if err := cd.checkPermission(ctx); err != nil {
		return code.Error(err)
	}
	args := struct {
		To        []byte `json:"to",required:"true"`
		Amount    []byte `json:"amount",required:"true"`
		Timestamp []byte `json:"timestamp",required:"true"`
		Comments  []byte `json:"comments",required:"comment"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	in := map[string][]byte{
		COST_COUNT:  nil,
		TOTAL_COSTS: nil,
		BALANCE:     nil,
	}
	if err := cd.batchGet(ctx, &in); err != nil {
		return code.Error(err)
	}
	if utils.Compare(in[BALANCE], args.Amount) < 0 {
		return code.Error(utils.ErrBalanceLow)
	}
	totalCost := utils.Add(in[TOTAL_COSTS], args.Amount)
	balance := utils.Sub(in[BALANCE], args.Amount)
	costCount := utils.Add(in[COST_COUNT], 1) //TODO @fengjin
	costId := []byte{}                        //TODO @fengjin
	allCOstKey := utils.Concat(ALL_COST, costId)
	//_=map[[9]byte]string{} TODO @fengjin 这种格式的支持
	costDetails := utils.Concat(
		"to=", args.To,
		",ampunt=", args.Amount,
		",timeStamp:=", args.Timestamp,
		",comments=", args.Amount,
	)
	batch := map[string][]byte{
		string(allCOstKey): costDetails,
		string(costCount):  costCount,
		TOTAL_COSTS:        totalCost,
		BALANCE:            balance,
	}
	if err := cd.batchPut(ctx, batch); err != nil {
		return code.Error(err)
	}
	//_=map[[9]byte]string{}
	return code.OK(costId)
}

func (cd *charityDonation) Statistics(ctx code.Context) code.Response {
	in := map[string][]byte{
		TOTAL_RECEIVED: nil,
		TOTAL_COSTS:    nil,
		BALANCE:        nil,
	}
	if err := cd.batchGet(ctx, &in); err != nil {
		return code.Error(err)
	}

	return code.OK(utils.Concat( //TODO 其他的也修改成这种格式
		"totalDonates=", in[TOTAL_RECEIVED], ",",
		"totalCosts=", in[TOTAL_COSTS], ",",
		"fundBalance=", in[BALANCE], ",",
	))
}

func (cd *charityDonation) QueryDoner(ctx code.Context) code.Response {
	args := struct {
		Donar []byte `json:"donar",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	start := utils.Concat(USER_DONATE, args.Donar, "%")
	end := utils.Concat(start, "~")
	iter := ctx.NewIterator(start, end)
	donateCount := []byte("0") //TODO
	defer iter.Close()
	result := []byte{}
	for iter.Next() {
		if len(iter.Key()) > len(start) { //为什么这里需要检查长度呢？
			donateCount = utils.Add(donateCount, "1")
			donateId := iter.Key()[len(start)] //TODO @fengjin
			result = append(result, utils.Concat(
				"id=", donateId, ",",
				iter.Value(), "\n",
			)...)
		}
	}
	return code.OK(utils.Concat("total donate count:", donateCount, "\n", result))
}

func (cd *charityDonation) QueryDonates(ctx code.Context) code.Response {
	args := struct {
		StartId []byte `json:"startid",required:"true"`
		Limit   []byte `json:"limit,"required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if utils.Compare(args.Limit, MAX_LIMIT) > 0 { //TODO @fengjin
		return code.Error(ErrLimitExceeded)
	}
	donateKey := utils.Concat(ALL_DONATE, args.StartId)
	start := donateKey
	end := utils.Concat(donateKey, "~")
	iter := ctx.NewIterator(start, end)
	defer iter.Close()
	selected := 0
	result := []byte{}
	for iter.Next() { //Error 处理，可以作为一个通用的iter 处理逻辑，屏蔽close 等问题。考虑传一个函数/闭包
		if len(iter.Key()) < len([]byte(ALL_DONATE)) {
			continue
		}
		if utils.Compare(selected, args.Limit) > 0 {
			break
		}
		selected += 1
		donateID := iter.Key()[:len([]byte(ALL_DONATE))]
		result = append(result, utils.Concat(
			"id=", donateID, ",",
			iter.Value(), "\n",
		)...)
	}
	return code.OK(result)
}

//TODO Copy Past 代码是万恶之源
func (cd *charityDonation) QueryCosts(ctx code.Context) code.Response {

	args := struct {
		StartId []byte `json:"startid",required:"true"`
		Limit   []byte `json:"limit,"required:"true"` //TODO 数字类型添加溢出检测
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if utils.Compare(args.Limit, MAX_LIMIT) > 0 { //TODO @fengjin
		return code.Error(ErrLimitExceeded)
	}
	costKey := utils.Concat(ALL_COST, args.StartId)
	start := costKey
	end := utils.Concat(costKey, "~")
	iter := ctx.NewIterator(start, end) //要不也封装一层吧
	defer iter.Close()
	selected := 0
	result := []byte{}
	for iter.Next() { //Error 处理，可以作为一个通用的iter 处理逻辑，屏蔽close 等问题。考虑传一个函数/闭包
		if len(iter.Key()) < len([]byte(ALL_COST)) {
			continue
		}
		if utils.Compare(selected, args.Limit) > 0 {
			break
		}
		selected += 1
		costId := iter.Key()[:len([]byte(ALL_COST))]
		result = append(result, utils.Concat(
			"id=", costId, ",",
			iter.Value(), "\n",
		)...)
	}
	return code.OK(result)

}

func (cd *charityDonation) batchPut(ctx code.Context, batch map[string][]byte) error {
	for k, v := range batch {
		if err := ctx.PutObject(utils.Concat(k), v); err != nil {
			return err
		}
	}
	return nil
}

//TODO 验证下这么写是不是可以，尤其是一边读一边修改
func (cd *charityDonation) batchGet(ctx code.Context, out *map[string][]byte) error {
	for k, _ := range *out {
		tmp, err := ctx.GetObject(utils.Concat(k))
		if err != nil {
			return err
		}
		(*out)[k] = tmp
	}
	return nil
}

//装饰器模式？？
func (cd *charityDonation) checkPermission(ctx code.Context) error {
	caller := ctx.Initiator() //TODO @fengjin 和 caller 的区别
	if caller == "" {
		return utils.ErrMissingCaller
	}
	if caller != ADMIN {
		return utils.ErrPermissionDenied
	}
	return nil
}

func main() {
	driver.Serve(new(charityDonation))
}

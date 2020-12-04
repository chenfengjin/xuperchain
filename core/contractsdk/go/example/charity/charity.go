package main

import (
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
	utils2 "github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
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
	admin, err := ctx.GetObject(utils2.ConcatWithString(ADMIN))
	if err != nil { // 这里的err 什么情况，不存在会是err么？
		return code.Error(err)
	}
	//TODO using batch put
	if err := ctx.PutObject(utils2.ConcatWithString(ADMIN), admin); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils2.ConcatWithString(TOTAL_RECEIVED), utils2.ConcatWithString("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils2.ConcatWithString(TOTAL_COSTS), utils2.ConcatWithString("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils2.ConcatWithString(BALANCE), utils2.ConcatWithString("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils2.ConcatWithString(DONATE_COUNT), utils2.ConcatWithString("0")); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils2.ConcatWithString(COST_COUNT), utils2.ConcatWithString("0")); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (cd *charityDonation) Donate(ctx code.Context) code.Response {
	cd.checkPermission(ctx)
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
	totalReceived := utils2.Add(in[TOTAL_RECEIVED], args.Amount)
	balance := utils2.Add(in[BALANCE], args.Amount)
	donate := string(utils2.Sub(in[DONATE_COUNT], "1"))
	donateID := 0 // TODO cpp的方案是填充0到20位
	userDonateKey := string(utils2.ConcatWithString(USER_DONATE, args.Donor, "%", donateID))
	allDonateKey := string(utils2.ConcatWithString(ALL_DONATE, donateID))
	donateDetail := utils2.ConcatWithString("donor=", args.Donor, ",amount=", args.Amount,
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
	return code.OK(utils2.ConcatWithString(donateID))

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
	if utils2.Compare(in[BALANCE], args.Amount) < 0 {
		return code.Error(utils2.ErrBalanceLow)
	}
	totalCost := utils2.Add(in[TOTAL_COSTS], args.Amount)
	balance := utils2.Sub(in[BALANCE], args.Amount)
	costCount := utils2.Add(in[COST_COUNT], 1) //TODO @fengjin
	costId := []byte{}                         //TODO @fengjin
	allCOstKey := utils2.ConcatWithString(ALL_COST, costId)
	//_=map[[9]byte]string{} TODO @fengjin 这种格式的支持
	costDetails := utils2.ConcatWithString(
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

	return code.OK(utils2.ConcatWithString( //TODO 其他的也修改成这种格式
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
	start := utils2.ConcatWithString(USER_DONATE, args.Donar, "%")
	end := utils2.ConcatWithString(start, "~")
	iter := ctx.NewIterator(start, end)
	donateCount := []byte("0") //TODO
	defer iter.Close()
	result := []byte{}
	for iter.Next() {
		if len(iter.Key()) > len(start) { //为什么这里需要检查长度呢？
			donateCount = utils2.Add(donateCount, "1")
			donateId := iter.Key()[len(start)] //TODO @fengjin
			result = append(result, utils2.ConcatWithString(
				"id=", donateId, ",",
				iter.Value(), "\n",
			)...)
		}
	}
	return code.OK(utils2.ConcatWithString("total donate count:", donateCount, "\n", result))
}

func (cd *charityDonation) QueryDonates(ctx code.Context) code.Response {
	args := struct {
		StartId []byte `json:"startid",required:"true"`
		Limit   []byte `json:"limit,"required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if utils2.Compare(args.Limit, MAX_LIMIT) > 0 { //TODO @fengjin
		return code.Error(ErrLimitExceeded)
	}
	donateKey := utils2.ConcatWithString(ALL_DONATE, args.StartId)
	start := donateKey
	end := utils2.ConcatWithString(donateKey, "~")
	iter := ctx.NewIterator(start, end)
	defer iter.Close()
	selected := 0
	result := []byte{}
	for iter.Next() { //Error 处理，可以作为一个通用的iter 处理逻辑，屏蔽close 等问题。考虑传一个函数/闭包
		if len(iter.Key()) < len([]byte(ALL_DONATE)) {
			continue
		}
		if utils2.Compare(selected, args.Limit) > 0 {
			break
		}
		selected += 1
		donateID := iter.Key()[:len([]byte(ALL_DONATE))]
		result = append(result, utils2.ConcatWithString(
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
	if utils2.Compare(args.Limit, MAX_LIMIT) > 0 { //TODO @fengjin
		return code.Error(ErrLimitExceeded)
	}
	costKey := utils2.ConcatWithString(ALL_COST, args.StartId)
	start := costKey
	end := utils2.ConcatWithString(costKey, "~")
	iter := ctx.NewIterator(start, end) //要不也封装一层吧
	defer iter.Close()
	selected := 0
	result := []byte{}
	for iter.Next() { //Error 处理，可以作为一个通用的iter 处理逻辑，屏蔽close 等问题。考虑传一个函数/闭包
		if len(iter.Key()) < len([]byte(ALL_COST)) {
			continue
		}
		if utils2.Compare(selected, args.Limit) > 0 {
			break
		}
		selected += 1
		costId := iter.Key()[:len([]byte(ALL_COST))]
		result = append(result, utils2.ConcatWithString(
			"id=", costId, ",",
			iter.Value(), "\n",
		)...)
	}
	return code.OK(result)

}

func (cd *charityDonation) batchPut(ctx code.Context, batch map[string][]byte) error {
	for k, v := range batch {
		if err := ctx.PutObject(utils2.ConcatWithString(k), v); err != nil {
			return err
		}
	}
	return nil
}

//TODO 验证下这么写是不是可以，尤其是一边读一边修改
func (cd *charityDonation) batchGet(ctx code.Context, out *map[string][]byte) error {
	for k, _ := range *out {
		tmp, err := ctx.GetObject(utils2.ConcatWithString(k))
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
		return utils2.ErrMissingCaller
	}
	if caller != ADMIN {
		return utils2.ErrPermissionDenied
	}
	return nil
}

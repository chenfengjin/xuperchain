package main

import (
	"bytes"
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
	utils2 "github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
)

const (
	BALANCEPRE   = "balanceOf_"
	ALLOWANCEPRE = "allowanceOf_"
	MASTERPRE    = "owner"
)

type awardManage struct{}

func (am *awardManage) Initialize(ctx code.Context) code.Response {
	args := struct {
		totalSupply []byte `json:"totalSupply",gt:"0"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}

	caller := ctx.Initiator()
	if len(caller) == 0 {
		return code.Error(errors.New(""))
	}

	if err := ctx.PutObject([]byte(BALANCEPRE+caller), args.totalSupply); err != nil {
		return code.Error(err)
	}

	if err := ctx.PutObject([]byte("totalSupply"), args.totalSupply); err != nil {
		return code.Error(err)
	}

	if err := ctx.PutObject(utils2.Concat(MASTERPRE), utils2.Concat(caller)); err != nil {
		return code.Error(err)
	}

	return code.OK(nil)

}

func (am *awardManage) AddAward(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	master, err := ctx.GetObject([]byte(MASTERPRE))
	if err != nil {
		return code.Error(errors.New(""))

	}
	if !bytes.Equal(master, []byte(caller)) { //TODO equal?
		return code.Error(errors.New(""))
	}
	args := struct {
		Amount []byte `json:"amount"`
		Value  []byte `json:"value"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}

	totalSupply := utils2.Add(args.Amount, args.Value)
	ctx.PutObject([]byte("totalSupply"), totalSupply)
	return code.OK([]byte("ok"))
}

func (am *awardManage) TotalSupply(ctx code.Context) code.Response {

	if value, err := ctx.GetObject([]byte("totalSupply")); err != nil {
		return code.Error(err)
	} else {
		return code.OK(value)
	}

}

func (am *awardManage) Balance(ctx code.Context) code.Response {
	args := struct {
		Caller []byte `json:"caller"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}

	value, err := ctx.GetObject(append([]byte(BALANCEPRE), args.Caller...))
	if err != nil {
		return code.Error(err)
	}
	return code.OK(value)
}

func (am *awardManage) Allowance(ctx code.Context) code.Response {
	args := struct {
		From []byte `json:"from"`
		To   []byte `json:"to"`
	}{}
	utils.Validate(ctx.Args(), &args)

	buf := bytes.NewBufferString(ALLOWANCEPRE)
	buf.Write(args.From)
	buf.WriteString("_")
	buf.Write(args.To)

	value, err := ctx.GetObject(buf.Bytes())
	if err != nil {
		return code.Error(err)
	}
	return code.OK(value)

}

func (am *awardManage) Transfer(ctx code.Context) code.Response {

	args := struct {
		From   []byte `json:"from",required:"true",eq:"",neq:"",lt:"",gt:""`
		To     []byte `json:"to",required:"true"`
		Caller string `json:"caller",required:"true"`
		Token  []byte `json:"token",required:"true"`
		Raw    map[string][]byte
	}{}

	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if utils2.Compare(args.From, args.Token) == 0 {
		return code.Error(errors.New("can not transfer to yourself"))
	}

	from_balance, err := ctx.GetObject((append([]byte(BALANCEPRE), args.From...)))
	if err != nil {
	}
	if utils2.Compare(from_balance, args.Token) < 0 {
		return code.Error(nil)
	}
	to_balance, err := ctx.GetObject((append([]byte(BALANCEPRE), args.To...)))
	if err != nil {
		return code.Error(err)
	}

	to_balance = utils2.Add(to_balance, args.Token)
	from_balance = utils2.Sub(from_balance, args.Token)
	return code.OK([]byte("ok"))

}

func (am *awardManage) TransferFrom(ctx code.Context) code.Response {
	args := struct {
		From   string `json:"from",required:"true"`
		To     string `json:"to",required:"true"`
		Caller string `json:"caller",required:"true"`
		Token  []byte `json:"token",required:"true"`
	}{}

	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}

	value, err := ctx.GetObject([]byte(ALLOWANCEPRE + args.From + "_" + args.Caller))
	if err != nil {
		return code.Error(err)

	}
	if utils2.Compare(value, args.Token) < 0 {
		return code.Error(utils.ErrBalanceLow)
	}

	from_balance, err := ctx.GetObject([]byte(BALANCEPRE + args.From))
	if err != nil {
		return code.Error(err)
	}
	if utils2.Compare(from_balance, args.Token) < 0 {
		return code.Error(utils.ErrBalanceLow)
	}
	to_balance, err := ctx.GetObject([]byte(BALANCEPRE + args.To))
	if err != nil {
		return code.Error(err)
	}
	_ = to_balance
	allowance_balance := []byte{} //TODO
	allowance_balance = utils2.Sub(allowance_balance, args.Token)
	allowance_key := []byte("") // TODO
	ctx.PutObject(allowance_key, allowance_balance)
	return code.OK(nil)

}

func (am *awardManage) Approve(ctx code.Context) code.Response {
	args := struct {
		From         []byte `json:"from",required:"true"`
		To           []byte `json:"to",required:"true"`
		Caller       []byte `json:"caller",required:"true"`
		Token        []byte `json:"token",required:"true"`
		ValidateFunc func() error
	}{}

	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	value, err := ctx.GetObject(utils2.Concat(ALLOWANCEPRE, args.From, "_", args.To))
	if err != nil {

	}

	from_balance, err := ctx.GetObject(utils2.Concat(BALANCEPRE, args.From))
	if err != nil {
		return code.Error(err)
	}

	if utils2.Compare(value, args.Token) < 0 {
		return code.Error(utils.ErrBalanceLow)
	}
	if utils2.Compare(from_balance, args.Token) < 0 {
		return code.Error(utils.ErrBalanceLow)
	}
	to_balance, err := ctx.GetObject(utils2.Concat(BALANCEPRE, args.To))
	if err != nil {
		return code.Error(err)
	}
	allowance_balance := []byte{} //TODO
	allowance_balance = utils2.Sub(allowance_balance, args.Token)
	allowance_key := []byte("")
	ctx.PutObject(allowance_key, to_balance)
	return code.OK(nil)
}

func main() {
	driver.Serve(new(awardManage))
}

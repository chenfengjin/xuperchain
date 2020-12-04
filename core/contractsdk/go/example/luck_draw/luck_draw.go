package main

import (
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
	"math/rand"
)

const (
	USERID  = "Userid"
	TICKTID = "Luckid" // copy from cpp code
	ADMIN   = "admin"
	RESULT  = "result"
	TICKETS = "tickets"
)

type luckDraw struct {
}

func (ld *luckDraw) Initialize(ctx code.Context) code.Response {
	args := struct {
		Admin []byte `json:"admin"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.ConcatWithString(ADMIN), args.Admin); err != nil {
		return code.Error(err)
	}
	err := ctx.PutObject(utils.ConcatWithString("tickets"), utils.ConcatWithString("0"))
	if err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

// TODO @fengjin 这里的 caller 什么意思呢,以及类型？
func (ld *luckDraw) IsAdmin(ctx code.Context, caller string) bool {
	admin, err := ctx.GetObject(utils.ConcatWithString(ADMIN))
	if err != nil {
		return false
	}
	return string(admin) == caller
}

func (ld *luckDraw) GetLuckId(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	_, err := ctx.GetObject(utils.ConcatWithString(RESULT))
	if err == nil { //TODO @fengjin
		return code.Error(errors.New(" the lock draw is finished"))
	}
	if userVal, err := ctx.GetObject(utils.ConcatWithString(USERID, caller)); err != nil {
		return code.OK(userVal)
	}
	lastId, err := ctx.GetObject(utils.ConcatWithString(TICKETS))
	if err != nil {
		return code.Error(err)
	}
	lastId = utils.Add(lastId, []byte("1"))
	{
		if err := ctx.PutObject(utils.ConcatWithString(USERID, caller), lastId); err != nil {
			return code.Error(err)
		}
		if err := ctx.PutObject(utils.ConcatWithString(TICKTID, lastId), utils.ConcatWithString(caller)); err != nil {
			return code.Error(err)
		}
		if err := ctx.PutObject(utils.ConcatWithString(TICKETS), lastId); err != nil {
			return code.Error(err)
		}
	}
	return code.OK(lastId)
}

func (ld *luckDraw) StartLuckDraw(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	if !ld.IsAdmin(ctx, caller) {
		return code.Error(utils.ErrPermissionDenied)
	}
	args := struct {
		Seed []byte `json:"seed",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	//	TODO seed 的格式
	lastId, err := ctx.GetObject(utils.ConcatWithString(TICKETS))
	if err != nil {
		return code.Error(err)
	}
	rand.Seed(int64((1)))
	//rand.Seed(args.Seed) // TODO @fengjin
	luckid := rand.Int63()%int64(lastId) + 1 //TODO @fengjin
	//	if lastid==0??
	if luckUser, err := ctx.GetObject(utils.ConcatWithString(TICKTID, luckid)); err != nil {
		return code.Error(err)
	} else {
		return code.OK(luckUser)
	}
}

func (ld *luckDraw) GetResult(ctx code.Context) code.Response {
	if luckUser, err := ctx.GetObject(utils.ConcatWithString(RESULT)); err != nil {
		return code.Error(err)
	} else {
		return code.OK(luckUser)
	}
}

func main() {
	driver.Serve(new(luckDraw))
}

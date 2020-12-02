package main

import (
	"errors"
	utils "github.com/xuperchain/xuperchain/core/contractsdk/go"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/unmarshal"
	"math/rand"
)

const (
	USERID  = "Userid"
	TICKTID = "Luckid" // copy from cpp code
	ADMIN   = "admin"
	RESULT  = "result"
	TICKETS = "tickets"
)

var (
	ErrPermissionDenied = errors.New("only the admin can add new asset type")
)

type luckDraw struct {
}

func (ld *luckDraw) Initialize(ctx code.Context) code.Response {
	args := struct {
		Admin []byte `json:"admin"`
	}{}
	if err := unmarshal.Vaildate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.ContactWithString(ADMIN), args.Admin); err != nil {
		return code.Error(err)
	}
	err := ctx.PutObject(utils.ContactWithString("tickets"), utils.ContactWithString("0"))
	if err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

// TODO @fengjin 这里的 caller 什么意思呢,以及类型？
func (ld *luckDraw) IsAdmin(ctx code.Context, caller string) bool {
	admin, err := ctx.GetObject(utils.ContactWithString(ADMIN))
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
	_, err := ctx.GetObject(utils.ContactWithString(RESULT))
	if err == nil { //TODO @fengjin
		return code.Error(errors.New(" the lock draw is finished"))
	}
	if userVal, err := ctx.GetObject(utils.ContactWithString(USERID, caller)); err != nil {
		return code.OK(userVal)
	}
	lastId, err := ctx.GetObject(utils.ContactWithString(TICKETS))
	if err != nil {
		return code.Error(err)
	}
	lastId = utils.Add(lastId, []byte("1"))
	{
		if err := ctx.PutObject(utils.ContactWithString(USERID, caller), lastId); err != nil {
			return code.Error(err)
		}
		if err := ctx.PutObject(utils.ContactWithString(TICKTID, lastId), utils.ContactWithString(caller)); err != nil {
			return code.Error(err)
		}
		if err := ctx.PutObject(utils.ContactWithString(TICKETS), lastId); err != nil {
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
		return code.Error(ErrPermissionDenied)
	}
	args := struct {
		Seed []byte `json:"seed",required:"true"`
	}{}
	if err := unmarshal.Vaildate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	//	TODO seed 的格式
	lastId, err := ctx.GetObject(utils.ContactWithString(TICKETS))
	if err != nil {
		return code.Error(err)
	}
	rand.Seed(int64((1)))
	//rand.Seed(args.Seed) // TODO @fengjin
	luckid := rand.Int63()%int64(lastId) + 1 //TODO @fengjin
	//	if lastid==0??
	if luckUser, err := ctx.GetObject(utils.ContactWithString(TICKTID, luckid)); err != nil {
		return code.Error(err)
	} else {
		return code.OK(luckUser)
	}
}

func (ld *luckDraw) GetResult(ctx code.Context) code.Response {
	if luckUser, err := ctx.GetObject(utils.ContactWithString(RESULT)); err != nil {
		return code.Error(err)
	} else {
		return code.OK(luckUser)
	}
}

func main() {
	driver.Serve(new(luckDraw))
}

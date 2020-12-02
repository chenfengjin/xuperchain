package main

import (
	utils "github.com/xuperchain/xuperchain/core/contractsdk/go"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/unmarshal"
)

const (
	//TODO with fengjin 考虑和c++ 兼容？？？
	OWNER_KEY  = "owner"
	RECORD_KEY = "R_"
)

type scoreRecord struct {
}

func (sr *scoreRecord) Initialize(ctx code.Context) code.Response {
	args := struct {
		Owner []byte `json:"owner",required:"true"`
	}{}
	if err := unmarshal.Vaildate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.ContactWithString(OWNER_KEY), args.Owner); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (sc *scoreRecord) AddScore(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	owner, err := ctx.GetObject(utils.ContactWithString(OWNER_KEY))
	if err != nil {
		return code.Error(err)
	}
	if utils.Compare(owner, []byte(caller)) != 0 {
		return code.Error(utils.ErrPermissionDenied)
	}
	args := struct {
		UserId []byte `json:"user_id",required:"true"`
		Data   []byte `json:"data",required:"data"`
	}{}
	if err := unmarshal.Vaildate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.ContactWithString(RECORD_KEY, args.UserId), args.Data); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (sr *scoreRecord) QueryScore(ctx code.Context) code.Response {
	args := struct {
		UserId []byte `json:"userid",required:"true"`
	}{}
	if err := unmarshal.Vaildate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if data, err := ctx.GetObject(utils.ContactWithString(RECORD_KEY, args.UserId)); err != nil {
		return code.Error(err)
	} else {
		return code.OK(data)
	}
}

func (sr *scoreRecord) QueryOwner(ctx code.Context) code.Response {
	owner, err := ctx.GetObject(utils.ContactWithString(OWNER_KEY))
	if err != nil {
		return code.Error(err)
	}
	return code.OK(owner)
}

func main() {

	driver.Serve(new(scoreRecord))
}

package main

import (
	"bytes"
	"fmt"
	utils "github.com/xuperchain/xuperchain/core/contractsdk/go"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/unmarshal"
	"strings"
)

const (
	GOODS          = "GOODS_"
	GOODSRECORD    = "GOODSSRECORD_"
	GOODSRECORDTOP = "GOODSSRECORDTOP_"
	CREATE         = "CREATE"
	ADMIN          = "admin"
)

type sourceTrace struct {
}

func (st *sourceTrace) Initialize(ctx code.Context) code.Response {
	args := struct {
		Admin []byte `json:"admin",required:"true"`
	}{}
	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	ctx.PutObject(utils.ConcatWithString(ADMIN), args.Admin)
	return code.OK(nil)
}

func (st *sourceTrace) CreateGoods(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}

	admin, err := ctx.GetObject(utils.ConcatWithString(ADMIN))
	if err != nil {
		return code.Error(err)
	}

	if utils.Compare(admin, []byte(caller)) == 0 {
		return code.Error(utils.ErrPermissionDenied)
	}
	args := struct {
		Id   []byte `json:"id",required:"true''"`
		Desc []byte `json:"desc",required:"desc"`
	}{}
	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	goodsKey := utils.ConcatWithString(GOODS, args.Id)
	if err := utils.CheckExist(ctx, goodsKey); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(goodsKey, args.Desc); err != nil {
		return code.Error(err)
	}

	goodsRecordsKey := utils.ConcatWithString(GOODSRECORD, args.Id, "_0")
	goodsRecordsTopKey := utils.ConcatWithString(GOODSRECORDTOP, args.Id)
	if err := ctx.PutObject(goodsRecordsKey, utils.ConcatWithString(CREATE)); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(goodsRecordsTopKey, 0); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (st *sourceTrace) updateGoods(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}

	admin, err := ctx.GetObject(utils.ConcatWithString(ADMIN))
	if err != nil {
		return code.Error(err)
	}

	if utils.Compare(admin, []byte(caller)) == 0 {
		return code.Error(utils.ErrPermissionDenied)
	}
	args := struct {
		Id     []byte `json:"id",required:"true"`
		Reason []byte `json:"reason",required:"true"`
	}{}

	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	value, err := ctx.GetObject(utils.ConcatWithString(GOODSRECORDTOP, args.Id))
	if err != nil {
		return code.Error(err)
	}
	topRecord := utils.Add(value, []byte("1"))

	if err := ctx.PutObject(utils.ConcatWithString(GOODSRECORD, args.Id, "_", topRecord), args.Reason); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.ConcatWithString(GOODSRECORDTOP, args.Id), topRecord); err != nil {
		return code.Error(err)
	}
	return code.OK(topRecord)
}

func (st *sourceTrace) QueryRecords(ctx code.Context) code.Response {
	args := struct {
		Id []byte `json:"id",required:"true"`
	}{}
	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	//TODO @fengjin 不存在的时候error 是什么情况呢?
	value, err := ctx.GetObject(utils.ConcatWithString(GOODS, args.Id))
	_ = value // TODO @fengjin
	if err != nil {
		return code.Error(err)
	}
	goodsRecordsKey := utils.ConcatWithString(GOODSRECORD, args.Id, "_")
	start := goodsRecordsKey
	end := utils.ConcatWithString(start, "~")
	iter := ctx.NewIterator(start, end)
	result := bytes.NewBuffer(nil)
	for iter.Next() {
		//TODO error 处理
		goodsRecord := string(iter.Key())[:len(GOODSRECORD)] //TODO @fengjin 确认下标没问题
		pos := strings.Index(goodsRecord, "_")
		goodsId := goodsRecord[:pos]
		updateRecord := goodsRecord[pos+1:]
		reason := iter.Value()
		//result = append(result,utils.ConcatWithString("goodsId=",goodsId,))
		result.WriteString(fmt.Sprintf("goodsID=%s,updateRecord=%s,reason=%s,\n", goodsId, updateRecord, reason))
	}
	return code.OK(result.Bytes())
}

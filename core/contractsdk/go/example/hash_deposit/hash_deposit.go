package main

import (
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
)

const (
	UserBucket = "USER"
	HashBucket = "HASH"
)

type hashDeposit struct {
}

func (hd *hashDeposit) Initialize(ctx code.Context) code.Response {
	return code.OK(nil)
}

func (hd *hashDeposit) StoreFileInfo(ctx code.Context) code.Response {
	args := struct {
		UsedID   []byte `json:"user_id",required:"true"`
		HashID   []byte `json:"hash_id",required:"true"`
		FileName []byte `json:"file_name",required:"true"`
	}{}
	err := utils.Validate(ctx.Args(), &args)
	if err != nil {
		return code.Error(err)
	}
	userKey := utils.Concat(UserBucket, "/", args.UsedID, "/", args.HashID)
	hashKey := utils.Concat(HashBucket, "/", args.HashID)
	value := utils.Concat(args.UsedID, "\t", args.HashID, "\t", args.FileName)

	//TODO
	// error == nil means hash exists already
	if _, err = ctx.GetObject(utils.Concat(hashKey)); err == nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(userKey, value); err != nil {
		return code.Error(err)
	}

	if err := ctx.PutObject(hashKey, value); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (hd *hashDeposit) queryUserList(ctx code.Context) code.Response {
	key := utils.Concat(UserBucket, "/")
	iter := ctx.NewIterator(key, utils.Concat(key, "~"))
	result := []byte{}
	for iter.Next() {
		//TODO error 处理@fengjin
		k := string(iter.Key())
		v := iter.Value()
		if len(k) > len(UserBucket)+1 {
			result = append(result, utils.Concat(v[len(UserBucket)+1:], "\n")...)
		}
	}
	return code.OK(utils.Concat(result))
}

func (hd *hashDeposit) QueryFileInfoByUser(ctx code.Context) code.Response {
	args := struct {
		UserID []byte `json:"user_id",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	result := []byte{}
	start := utils.Concat(UserBucket, "/", args.UserID)
	end := utils.Concat(start, "~")
	iter := ctx.NewIterator(start, end)
	for iter.Next() {
		//TODO error 处理 @fengjin
		result = append(result, utils.Concat(iter.Value(), "\n")...)
	}
	return code.OK(result)
}

func (hd *hashDeposit) QueryFileInfoByHash(ctx code.Context) code.Response {
	args := struct {
		HashID []byte `json:"hash_id",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	key := utils.Concat(HashBucket, "/", args.HashID)
	value, err := ctx.GetObject(key)
	if err != nil {
		return code.Error(err)
	}
	return code.OK(value)
}

func main() {
	driver.Serve(new(hashDeposit))
}

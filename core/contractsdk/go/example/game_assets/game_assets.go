package main

import (
	utils "github.com/xuperchain/xuperchain/core/contractsdk/go"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/unmarshal"
)

type gameAssets struct {
}

const (
	ADMIN      = "admin"
	ASSETTYPE  = "AssetType_"
	USERASSET  = "UserAsset_"
	ASSET2USER = "Asset2User_"
)

func (ga *gameAssets) Initialize(ctx code.Context) code.Response {
	args := struct {
		Admin []byte `json:"admin",required:"true"`
	}{}

	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(utils.ConcatWithString(ADMIN), args.Admin); err != nil {
		return code.Error(err)
	} else {
		return code.OK(nil)
	}

}

//TODO @fengjin
func (ga *gameAssets) isAdmin(ctx code.Context, caller string) bool {
	admin, err := ctx.GetObject(utils.ConcatWithString(ADMIN))
	if err != nil {
		//TODO 错误处理
		//return
	}
	return utils.Compare(admin, caller) == 0
}

func (ga *gameAssets) AddAssetType(ctx code.Context) code.Response {
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	if !ga.isAdmin(ctx, caller) {
		return code.Error(utils.ErrPermissionDenied)
	}
	args := struct {
		TypeID   []byte `json:"typeid",required:"true"`
		TypeDesc []byte `json:"typedesc",required:"true"`
	}{}
	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	assetKey := utils.ConcatWithString(ASSETTYPE, args.TypeID)
	if err := utils.CheckExist(ctx, assetKey); err != nil {
		return code.Error(err)
	}
	if err := ctx.PutObject(assetKey, args.TypeDesc); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (ga *gameAssets) ListAssetType(ctx code.Context) code.Response {
	start := utils.ConcatWithString(ASSETTYPE)
	end := utils.ConcatWithString(ASSETTYPE, "~")
	iter:=ctx.NewIterator(start,end)
	result:=[]byte{}
	for iter.Next(){
		if len(iter.Key())>len(ASSETTYPE){
			result = append(result,utils.ConcatWithString(iter.Key()[:len([]byte(ASSETTYPE)],":",iter.Value(),"\n")...))
		}
	}
	return code.OK(result)
}


func(ga*gameAssets)getAssetByUser(ctx code.Context)code.Response{
	//这里也不想搞了
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	if !ga.isAdmin(ctx, caller) {
		return code.Error(utils.ErrPermissionDenied)
	}
	args:= struct {
		UserID []byte `json:"userid",required:"false"`
	}{}
	if err:=unmarshal.Validate(ctx.Args(),&args);err!=nil{
		return code.Error(err)
	}
//	TODO 这一段太重复了，想办法抽象一下吧

//TODO @fengjin	空白slice 和nil以及slice的零值
	userId:=[]byte(caller)
	if args.UserID!=nil && len(args.UserID) >0{
		userId = args.UserID
	}
	userAssetKey:=utils.ConcatWithString(USERASSET,userId,"_")
	start:=userAssetKey
	end:=utils.ConcatWithString(start,"~")
	iter:=ctx.NewIterator(start,end)
	result:=[]byte{}
	//迭代获取也好重复
	for iter.Next(){
		if len(iter.Key())>len(userAssetKey){
			assetId:=iter.Key()[len(userAssetKey)]
			typeId:=iter.Value()
			assetTypeKey:=utils.ConcatWithString(ASSETTYPE,typeId)
			if assetDesc,err:=ctx.GetObject(assetTypeKey);err!=nil{
				continue
			//	TODO @fengjin
			}else{
				result = append(result, utils.ConcatWithString("assetid=",assetId,",typeid=",typeId,",assetDesc=",assetDesc,"\n")...)
			}
		}
	}
	return code.OK(result)
}

func(ga *gameAssets)NewAssetToUser(ctx code.Context)code.Response{
	caller := ctx.Initiator()
	if caller == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	if !ga.isAdmin(ctx, caller) {
		return code.Error(utils.ErrPermissionDenied)
	}
	args := struct {
		UserId []byte `json:"userid",required:"true"`
		TypeId   []byte `json:"typeid",required:"true"`
		AssetId []byte `json:"assetid",required:"true"`

	}{}
	if err := unmarshal.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	assetKey:=utils.ConcatWithString(ASSET2USER,args.AssetId)
	_,err:=ctx.GetObject(assetKey);
	if err!=nil{
		return code.Error(err)
	}
	userAssetKey:=utils.ConcatWithString(USERASSET,args.UserId,"_",args.AssetId)

	if err:=ctx.PutObject(userAssetKey,args.TypeId);err!=nil{
		return code.Error(err)
	}
	if err:=ctx.PutObject(assetKey,args.UserId);err!=nil{
		return code.Error(err)
	}
	return code.OK(args.AssetId)
}

func(ga *gameAssets)TradeAsset(ctx code.Context)code.Response{
	from := ctx.Initiator()
	if from == "" {
		return code.Error(utils.ErrMissingCaller)
	}
	args:= struct {
		To []byte `json:"to",required:"true"`
		AssetId []byte `json:"assetid",required:"true"`
	}{}
	if err:=unmarshal.Validate(ctx.Args(),&args);err!=nil{
		return code.Error(err)
	}
	userAssetKey:=utils.ConcatWithString(USERASSET,from,"_",args.AssetId)
	assetType,err:=ctx.GetObject(userAssetKey,);
	if err!=nil{
		return code.Error(err)
	}
	if err:=ctx.DeleteObject(userAssetKey);err!=nil{
		return code.Error(err)
	}

	assetKey:=utils.ConcatWithString(ASSET2USER,args.AssetId)
	newuserAssetKey:=utils.ConcatWithString(USERASSET,args.To,"_",args.AssetId)
	if err:=ctx.PutObject(newuserAssetKey,assetType);err!=nil{
		return code.Error(err)
	}
	if err:=ctx.PutObject(assetKey,args.To);err!=nil{
		return code.Error(err)
	}
	return code.OK(args.AssetId)
}

func main(){
	driver.Serve(new(gameAssets))
}







package com.baidu.xuper.example;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.baidu.xuper.Context;
import com.baidu.xuper.Contract;
import com.baidu.xuper.ContractMethod;
import com.baidu.xuper.Driver;
import com.baidu.xuper.Response;
import com.baidu.xuper.utils.ByteUtils;
import com.sun.tools.javac.util.ByteBuffer;

/**
 * Counter
 */
public class GameAssets implements Contract {
    final String ASSETTYPE = "AssetType_";
    final String USERASSET = "UserAsset";
    final String ASSET2USER = "Asset2User_";


    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        byte[] admin = ctx.args().get("admin");
        if (admin.length==0){
            return Response.error("missing admin address");
        }
        ctx.putObject("admin".getBytes(),admin);
        return Response.ok("ok".getBytes());
    }
    private boolean isAdmin(Context ctx){
        return this.isAdmin(ctx,ctx.caller());
    }
    private boolean isAdmin(Context ctx,String caller){
        byte[] admin = ctx.getObject("admin".getBytes());
        return ByteUtils.equal(admin,caller);
    }

    @ContractMethod
    public Response AddAssetType(Context ctx){
        if (!this.isAdmin(ctx)){
            return Response.error("only admin can add new asset type");
        }
        byte[]typeId = ctx.args().get("typeid");
        byte[]typeDesc = ctx.args().get("typedesc");// TODO @fengjin 向后兼容？？
        if (typeId.length == 0 || typeDesc.length ==0){
            return Response.error("missing typeid or typedesc");
        }
        byte[]assetTypeKey = ByteUtils.concat(ASSETTYPE,typeId);
        if (ctx.getObject(assetTypeKey).length !=0){
            return Response.error("type id does already exist");
        }
        ctx.putObject(assetTypeKey,typeDesc);
        return Response.ok(typeId);
    }

    @ContractMethod
    public Response listAssetType(Context ctx){
        ByteBuffer buf = new ByteBuffer();
        ctx.newIterator(ASSETTYPE.getBytes(),ByteUtils.concat(ASSETTYPE,"~")).forEachRemaining(
                elem->{
                    String assetType = elem.getKey();
                    buf.appendBytes();
                }
        );
    }

    public static void main(String[] args) {
        Driver.serve(new GameAssets());
    }
}
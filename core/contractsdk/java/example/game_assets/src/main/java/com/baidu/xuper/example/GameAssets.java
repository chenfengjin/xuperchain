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
        if (admin.length == 0) {
            return Response.error("missing admin address");
        }
        ctx.putObject("admin".getBytes(), admin);
        return Response.ok("ok".getBytes());
    }

    private boolean isAdmin(Context ctx) {
        return this.isAdmin(ctx, ctx.caller());
    }

    private boolean isAdmin(Context ctx, String caller) {
        byte[] admin = ctx.getObject("admin".getBytes());
        return ByteUtils.equal(admin, caller);
    }

    @ContractMethod
    public Response AddAssetType(Context ctx) {
        if (!this.isAdmin(ctx)) {
            return Response.error("only admin can add new asset type");
        }
        byte[] typeId = ctx.args().get("typeid");
        byte[] typeDesc = ctx.args().get("typedesc");// TODO @fengjin 向后兼容？？
        if (typeId.length == 0 || typeDesc.length == 0) {
            return Response.error("missing typeid or typedesc");
        }
        byte[] assetTypeKey = ByteUtils.concat(ASSETTYPE, typeId);
        if (ctx.getObject(assetTypeKey).length != 0) {
            return Response.error("type id does already exist");
        }
        ctx.putObject(assetTypeKey, typeDesc);
        return Response.ok(typeId);
    }

    @ContractMethod
    public Response listAssetType(Context ctx) {
        ByteBuffer buf = new ByteBuffer();
        ctx.newIterator(ASSETTYPE.getBytes(), ByteUtils.concat(ASSETTYPE, "~")).forEachRemaining(
                elem -> {
                    String assetType = ByteUtils.toString(elem.getKey());
                    buf.appendBytes((assetType.substring(ASSETTYPE.length()) + ":" +
                            elem.getValue() +
                            "\n").getBytes());// TODO @fengjin silce 的tostring 方法，类型转换规则
                }
        );
        return Response.ok(buf.elems);
    }

    @ContractMethod
    public Response getAssetsByUser(Context ctx) {
        //        admin cat get asset of any user
        String userId = ctx.caller();
        if (isAdmin(ctx, userId)) {
            if (ctx.args().get("userid").length != 0) { // TODO 参数格式
                userId = ByteUtils.toString(ctx.args().get("user_id"));
            }
        }
        byte[] userAsstKey = ByteUtils.concat(ByteUtils.concat(USERASSET, userId), "_");
        byte[] start = userAsstKey;
        byte[] end = ByteUtils.concat(start, "~");
//         这里再封装一层？？传递一个函数？？
        ByteBuffer buf = new ByteBuffer();
        ctx.newIterator(start, end).forEachRemaining(
                elem -> {
                    if (elem.getKey().length > userAsstKey.toString().length()) { // TODO why
                        String assetId = ByteUtils.toString(elem.getKey()).substring(userAsstKey.length);
                        String typeId = ByteUtils.toString(elem.getValue());
                        String assetTypeKey = ASSETTYPE + typeId;
                        String assetDesc = ByteUtils.toString(ctx.getObject(assetTypeKey.getBytes()));    // TODO 检查？？
                        buf.appendBytes(("assetid=" + assetId + ",typeid=" + typeId + ",assetDesc=" + assetDesc + "\n").getBytes());
                    }
                }
        );
        return Response.ok(buf.elems);
    }

    @ContractMethod
    public Response newAssetToUser(Context ctx) {
        if (!this.isAdmin(ctx)) {
            return Response.error("only admin can add new asset type");
        }
        byte[] userId = ctx.args().get("user_id");
        byte[] typeId = ctx.args().get("type_id");
        byte[] assetId = ctx.args().get("asset_id");
        if (userId.length == 0 || typeId.length == 0 || assetId.length == 0) {
            return Response.error("missing user_id or type_id or asst_id");
        }
        byte[] assetKey = ByteUtils.concat(ASSET2USER, assetId);
//        这里的检查是需要的么？？
        if (ctx.getObject(assetKey).length > 0) {
            return Response.error("asset id already exist");
        }

        byte[] userAssetKey = (USERASSET + ByteUtils.toString(userId) + "_" + ByteUtils.toString(assetId)).getBytes();
        ctx.putObject(userAssetKey, typeId);
        ctx.putObject(assetKey, userId);
        return Response.ok(assetId);
    }

    public Response tradeAsset(Context ctx) {
        byte[] from = ctx.caller().getBytes();// TODO ok?
        if (from.length == 0) {
            return Response.error("missing initiator"); // TODO why?
        }
        byte[] to = ctx.args().get("to");
        byte[] assetId = ctx.args().get("assetid"); // TODO??
        if (to.length == 0 || assetId.length == 0) {
            return Response.error("missing to or assetid");
        }
        byte[] userAssetKey = (USERASSET + ByteUtils.toString(from) + "_" + ByteUtils.toString(assetId)).getBytes();
        byte[] assetType = ctx.getObject(userAssetKey);
        if (assetType.length == 0) {
            return Response.error("you do not have asset with id " + ByteUtils.toString(assetId));
        }
        ctx.deleteObject(userAssetKey);
        byte[] assetKey = ByteUtils.concat(ASSET2USER, assetId);
        byte[] newUserAssetKey = (USERASSET + ByteUtils.toString(to) + "_" + ByteUtils.toString(assetId)).getBytes();
        ctx.putObject(newUserAssetKey, assetId);
        ctx.putObject(assetKey, to);
        return Response.ok(assetId);
    }

    public static void main(String[] args) {
        Driver.serve(new GameAssets());
    }
}
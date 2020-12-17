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
public class SourceTrace implements Contract {
    final String GOODS = "GOODS_";
    final String GOODSRECORD = "GOODSRECORD_";
    final String GOODSRECORDTOP = "GOODSRECORDTOP_";
    final String CREATE = "CREATE_";

    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        byte[] admin = ctx.args().get("admin".getBytes());
        if (admin.length == 0){
            return Response.error("missing admin address");
        }
        ctx.putObject("admin".getBytes(),admin);
        return Response.ok("ok".getBytes());
    }
    private boolean isAdmin(Context ctx, String caller){
        byte[]admin = ctx.getObject("admin".getBytes());
        return ByteUtils.equal(admin,caller);

    }
    private boolean isAdmin(Context ctx){
        String caller = ctx.caller();
        return isAdmin(ctx,caller);
    }

    @ContractMethod
    public  Response createGoods(Context ctx) {
        if (!isAdmin(ctx)) {
            return Response.error("only the admin can create new goods");
        }
        byte[] id = ctx.args().get("id".getBytes());
        byte[] desc = ctx.args().get("desc".getBytes());

        if (false) { // TODO
        }
        byte[]goodsKey = ByteUtils.concat(GOODS,id);
        if (ctx.getObject(goodsKey).length>0){
            return Response.error("the id  already exist, please check again");
        }
        ctx.putObject(goodsKey,desc);
        byte[] goodsRecordsKey  = ByteUtils.concat(GOODSRECORD.getBytes(),id,"_0");
        byte[]goodsRecordsTopKey = ByteUtils.concat(GOODSRECORDTOP,id);
        ctx.putObject(goodsRecordsKey,CREATE.getBytes());
        ctx.putObject(goodsRecordsTopKey,0); // TODO 0 的二进制表示还是用 "0"??
        return Response.ok(id);
    }

    @ContractMethod
    public Response updateGoods(Context ctx){
        if (!isAdmin(ctx)){
            return Response.error("only the admin can update goods");
        }
        byte[] id = ctx.args().get("id".getBytes());
        byte[] reason = ctx.args().get("reason".getBytes());
        if (id.length ==0 || reason.length==0){
            return Response.error("missing argument id or argument reason");
        }
        byte[]goodsRecordsTopKey = ByteUtils.concat(GOODSRECORDTOP,id);
        byte[]topRecord = ctx.getObject(goodsRecordsTopKey);
        topRecord = ByteUtils.increase(topRecord);
        byte[]goodsRecordsKey = ByteUtils.concat(GOODSRECORD,id,"_",topRecord); // TODO
        ctx.putObject(goodsRecordsKey,reason);
        ctx.putObject(goodsRecordsTopKey,topRecord);
        return Response.ok(topRecord);
    }
    @ContractMethod
    public Response queryRerords(Context ctx){
        byte[] id = ctx.args().get("id");
        if (id.length == 0){
            return Response.error("missing argument id");
        }

        byte[] goodsKey = ByteUtils.concat(GOODS,id);
        if (ctx.getObject(goodsKey).length ==0){
            return Response.error("good with id "+id+"not found"); // ID ??
        }
        byte[] goodsRecordKey = ByteUtils.concat(GOODSRECORD.getBytes(),id,"_");
        byte[] start = goodsKey;
        byte[] end = ByteUtils.concat(start,"~");
        ByteBuffer buf = new ByteBuffer();
        buf.appendBytes("\n".getBytes());

        ctx.newIterator(start,end).forEachRemaining(
                elem->{
                    String key = elem.getKey().toString();// TODO @fengjin
                    String[]  goodsRecords = key.substring(GOODSRECORD.length()).toString().split("_"); // TODO
                    String goodsId = goodsRecords[0];
                    String updateRecord = goodsRecords[1];
//                    TODO 防御性biancheng
                    // byte[]reason = elem.getValue();
                    String record = "goodIds=" +goodsId+",updateRecord="+updateRecord+",reason="+Reason+"\n"
                    buf.appendBytes(record.getBytes());
//                            TODO 考虑下append语义 and 性能问题
                }
        );
        return Response.ok(buf.elems);
    }

    public static void main(String[] args) {
        Driver.serve(new SourceTrace());
    }
}
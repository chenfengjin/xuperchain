package com.baidu.xuper;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

import com.baidu.xuper.*;
import com.baidu.xuper.ContractIteratorItem;

/**
 * Counter
 */
public class HashDeposit implements Contract {
        final byte [] Bucket = "USER".getBytes();
        final byte []HashBucket = "HASH".getBytes();

    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        return Response.ok("ok".getBytes());
    }

    @ContractMethod
    public Response storeFileInfo(Context ctx){
        byte[] user_id = ctx.args().get("user_id");
        byte[] hash_id = ctx.args().get("hash_id");
        byte[] file_name = ctx.args().get("file_name");
        byte[] userKey = null;
        byte[] hashKey = null;
        byte []value = null;
        if (ctx.getObject(hashKey)){
            return  Response.error("err");
        }
//        这里就不会出错吗 ？
        ctx.putObject(userKey,value);
        ctx.putObject(hashKey,value);
        return Response.ok("".getBytes());
    }

    @ContractMethod
    public Response queryUserList(Context ctx){
        String key = "";
        String start = "";
        String end = "";
       Iterator<ContractIteratorItem> iter = ctx.newIterator(start.getBytes(),end.getBytes());
       final ByteBuffer result = new ByteBuffer(0,0,0,0);
       iter.forEachRemaining(
               contractIteratorItem -> {
                   byte[]key = contractIteratorItem.getKey();
                   byte[]value = contractIteratorItem.getValue();
//                   if key.length > Userkey.length +1{
                   result.put(value);
//                   }
               }
       );
       return Response.ok(result.array());
    };

    @ContractMethod
    public Response queryFileInfoByUser(Context ctx){
        String key = "";
        String start = "";
        String end  = "";
//        并发安全性？？
        final ByteBuffer result = new ByteBuffer(0,0,0,0);
        ctx.newIterator(start.getBytes(),end.getBytes()).forEachRemaining(
                contractIteratorItem -> {
                    result.put(contractIteratorItem.getValue());
                    result.put("\n".getBytes());
                }
        );
        return Response.ok(result.array());

    }

    @ContractMethod
    public Response queryFileInfoByHash(Context ctx ){
        String key="";
        return Response.ok(ctx.getObject(key.getBytes()));
    }

    public static void main(String[] args) {
        Driver.serve(new HashDeposit());
    }
}
package com.baidu.xuper;

import com.sun.tools.javac.util.ByteBuffer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Counter
 */
public class LuckyDraw implements Contract {
    final String admin_key = "admin";
    final String userID = "result";
    final String result = "tickets";
    final String ticketId = "userid";
    final String tickets = "tickets";

    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        byte[] admin = ctx.args().get(admin_key);
        if (admin.length == 0){
            return Response.error("missing admin address")
        }
        ctx.putObject(admin,admin_key.getBytes());

        return Response.ok("ok".getBytes());
    }
    @ContractMethod
    public Response getLuckId(Context ctx){
        String caller = ctx.caller();
        if (caller.isEmpty()){
            return Response.error("missing initiator");
        }
        if (ctx.getObject(result.getBytes()).length !=0){
            return Response.error("this luck draw has finished");
        }
        byte[] userVal = ctx.getObject((userID+caller).getBytes());
        if (userVal.length >0){
            return Response.ok(null);
        }
        byte[] lastID = ctx.getObject(tickets.getBytes());
        // TODO error 处理
        try{
            lastID = AddOne(lastID);
        }catch (Exception e){
//            TODO
        }
        ByteBuffer buf =  new ByteBuffer();
        ctx.putObject((userID+caller).getBytes(),lastID);
//        ctx.putObject((ticketId.getBytes().lastID));  // TODO
        ctx.putObject(tickets.getBytes(),lastID);
        return Response.ok(lastID);
    }
    public Response startLuckDraw(Context ctx){

        String caller = ctx.caller();
        if (caller.isEmpty()){
            return Response.error("missing initiator");
        }
        byte[] admin = ctx.getObject(admin_key.getBytes());
        if (!Arrays.equals(caller.getBytes(),admin)){
            return Response.error("only the admin can add new asset type");
        }
        byte[]seed = ctx.args().get("seed");
        if (seed.length == 0){
            return Response.error("missing seed");
        }
        byte[]lastId = ctx.getObject(tickets.getBytes());
        int zero = 0;
        if (false){ // TODO
            return Response.error("no luck draw tickets");
        }
        Random rander = new Random(seed);
        long luckid  = (rander.nextLong() % lastId)+1;
        byte[] luckUser = ctx.getObject(ticketId+luckid);
        ctx.putObject(result.getBytes(),luckUser);
        return Response.ok(luckUser);
    }

    public Response getResult(Context ctx){
        byte[]luckUser = ctx.getObject(result.getBytes());
        if (luckUser.length==0){
            return Response.error("get luck draw result failed");
        }
        return Response.ok(luckUser);
    }

    public static void main(String[] args) {
        Driver.serve(new LuckyDraw());
    }
}
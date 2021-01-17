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
/**
 * Counter
 */
public class AwardManage implements Contract {
    final String BALANCE = "balanceOf_";
    final String ALLOWANCE = "allowanceOf_";
    final String MASTER = "owner";
    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        String caller = ctx.caller();
        if (caller.length()==0){
            return Response.error("missing caller");
        }
        byte[]totalSupply = ctx.args().get("totalSupply");
        if (totalSupply.length ==0){
            return Response.error("missing totalSupply");
        }
        if (ByteUtils.Less(totalSupply,0) || ByteUtils.Equal(totalSupply,0)){
            return Response.error("totalSupply is overflow");
        }
        ctx.putObject(ByteUtils.Concat(BALANCE,caller),totalSupply);
        ctx.putObject("totalSupply".getBytes(),totalSupply);
        ctx.putObject(MASTER.getBytes(),caller.getBytes());
        return Response.ok("ok".getBytes());
    }
//TODO @fengjin 考虑装饰器模式？？
    private boolean permCheck(Context ctx){
        return true;
    }

    public Response addAward(Context ctx){
        if (!permCheck(ctx)){
            return Response.error("you do not have permission to do this operation")
        }
        byte[] amount = ctx.args().get("amount".getBytes());
        if (!ByteUtils.More(amount,0)){
            return Response.error("amount must be positive");
        }

        byte[] totalSupply = ctx.getObject ("totalSupply".getBytes());
        if (!ByteUtils.More(value,0)){
            return Response.error("totalSupply must positive");
        }
        if (ByteUtils.Less(ByteUtils.Add(totalSupply,amount),0)){
            return Response.error("amount add to total supply overflow");
        }
        ctx.putObject("TotalSupply",BytesUtils.Add(totalSupply,amount));
        byte[] key = ByteUtils.Concat(BALANCE,ctx.caller());
        byte[] value = ByteUtils.Add(ctx.getObject(key),totalSupply)
        ctx.putObject(key,value)); // TODO @fengjin 溢出攻击
        return Response.ok(value);
    }

    public Response totalSupply(Context ctx){
        return Response.ok(ctx.getObject("totalSupply".getBytes()));
    }
    public Response  balance(Context ctx){
        byte[]caller = ctx.getObject("caller".getBytes());
        return Response.ok(ctx.getObject(ByteUtils.Concat(BALANCE,caller)));
    }
    public Response allowance(Context ctx){
        byte[] from = ctx.args().get("from".getBytes());
        byte[] to = ctx.args().get("to".getBytes());
        if (from.length == 0 || to.length == 0){
            return Response.error("missing from or to");
        }
        return ctx.getObject(ByteUtils.Concat(ALLOWANCE.getBytes(),from,"_".getBytes(),to))

    }

    @ContractMethod
    public Response transfer(Context ctx){

        byte[] from = ctx.args().get("from".getBytes());
        byte[] to = ctx.args().get("to".getBytes());
        byte[] token = ctx.args().get("token"); // TODO
        if(from.length==0 || to.length == 0 || token.length ==0){
            return Response.error("missing from or to or token");
        }
        if (ByteUtils.Equal(from,to)){
            return Response.error("you can not transfer to yourself");
        }
        if (ByteUtils.Less(token,0)|| ByteUtils.Equal(token,0)){
            return Response.error("token must be more than 0");
        }


        byte[] from_key = ByteUtils.Concat(BALANCE,from);
        byte[] from_balance = ctx.getObject(from_key);
        if (ByteUtils.Less(from_balance,token)){
            return Response.error("balance not enough");
        }
        byte[] to_key = ByteUtils.Concat(BALANCE,to);
        byte[]to_balance = ctx.getObject(to_key);
        from_balance = Byteutls.Sub(from_balance,token);

        ctx.putObject(from_key,ByteUtils.sub(from_balance,token));
        ctx.putObject(to_key,ByteUtils.add(to_balance,token));
        return Response.ok("transfer success".getBytes());
    }
    @ContractMethod
    public Response transferFrom(Context ctx){

        byte[] from = ctx.args().get("from".getBytes());
        byte[] to = ctx.args().get("to".getBytes());
        byte[] token = ctx.args().get("token"); // TODO
        byte[] caller = ctx.args().get("caller");
        if(from.length==0 || to.length == 0 || token.length ==0 || caller.length==0){
            return Response.error("missing from or to or token");
        }
        if (ByteUtils.Equal(from,to)){
            return Response.error("you can not transfer to yourself");
        }
        if (ByteUtils.Less(token,0)|| ByteUtils.Equal(token,0)){
            return Response.error("token must be more than 0");
        }
        byte[] allowance_key= ByteUtils.concat(ALLOWANCE,from,"_".getBytes(),caller);
        byte[] allowance_value = ctx.getObject(allowance_key);
        if (allowance_value.length == 0){
            return Response.error("you need to add allowance from_to");
        }
        if (ByteUtils.Less(allowance_value,token)) {
            return Response.error("The allowance of from_to not enough");
        }
        byte[] from_key = ByteUtils.concat(BALANCE,from);
        byte[] from_balance = ctx.getObject(from_key);

        if (from_balance.length ==0){
//            TODO
        }

        if (ByteUtils.Less(from_balance,token)){
            return Response.error("the balance of from not enough");
        }
        byte[]to_balance = ctx.getObject(ByteUtils.concat(BALANCE,to)){

        }
        ctx.putObject(from_key,ByteUtils.sub(from_balance,token));
        ctx.putObject(to_key,ByteUtils.add(to_balance,token));
        ctx.putObject(allowance_key,ByteUtils.sub(allowance_value,token));
    }
    @ContractMethod
    public Response approve(Context ctx){
        byte[] from = ctx.args().get("from".getBytes());
        byte[] to = ctx.args().get("to".getBytes());
        byte[] token = ctx.args().get("token"); // TODO
        if(from.length==0 || to.length == 0 || token.length ==0){
            return Response.error("missing from or to or token");
        }
        if (ByteUtils.Equal(from,to)){
            return Response.error("you can not transfer to yourself");
        }
        byte[] from_key = ByteUtils.Concat(BALANCE,from);
        byte[] from_balance = ctx.getObject(from_key);
        if (ByteUtils.Less(from_balance,token)){
            return Response.error("balance not enough");
        }
        byte[]allowance_key = ByteUtils.concat(ALLOWANCE,from,"_",to);
        byte[]allowance_balance = ByteUtils.add(ctx.getObject(allowance_key),token);
        ctx.putObject(allowance_key,allowance_balance);
        return Response.ok("approve succeed".getBytes());

    }

    public static void main(String[] args) {
        Driver.serve(new AwardManage());
    }
}
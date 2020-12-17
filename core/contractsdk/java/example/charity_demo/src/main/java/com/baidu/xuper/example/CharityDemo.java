package com.baidu.xuper.example;

import java.util.concurrent.atomic.AtomicInteger;

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
public class CharityDemo implements Contract {
    final String USERDONATE = "UserDonate_";
    final String ALLDONATE = "AllDonate_";
    final String ALLCOST = "AllCost_";
    final String TOTALRECEIVED = "TotalDonates";
    final String TOTALCOSTS = "TotalCosts";
    final String BALANCE = "Balance_";
    final String DONATECOUNT = "DonateCount_";
    final String COSTCOUNT = "CostCount_";
    final String ADMIN = "admin";
    final int MAX_LIMIT = 100; // uint64???


    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        byte[] admin = ctx.args().get("admin".getBytes());
        ctx.putObject(ADMIN.getBytes(),admin);
        ctx.putObject(TOTALRECEIVED.getBytes(),"0".getBytes()); // TODO 直接用数字??
        ctx.putObject(TOTALCOSTS.getBytes(),"0".getBytes());
        ctx.putObject(BALANCE.getBytes(),"0".getBytes());
        ctx.putObject(DONATECOUNT.getBytes(),"0".getBytes());
        ctx.putObject(COSTCOUNT.getBytes(),"0".getBytes());
        return Response.ok("ok".getBytes());
    }
    private boolean isAdmin(Context ctx){
        String caller = ctx.caller();
        return ByteUtils.equal(ctx.getObject(ADMIN.getBytes()),caller);
    }


    @ContractMethod
    public Response donate(Context ctx){
        if (!this.isAdmin(ctx)){
            return Response.error("only admin can add donate record");
        }
        byte[]donor = ctx.args().get("donor");
        byte[]amount = ctx.args().get("amount");
        byte []timestamp = ctx.args().get("timestamp");
        byte[]comments = ctx.args().get("comments");
        if(donor.length ==0 || amount.length == 0||timestamp.length ==0 || comments.length ==0){
            return Response.error("missing donor or amount or timestamp or comments");
        }
        byte[]donateCount = ctx.getObject(DONATECOUNT.getBytes());
        byte[]totalRecv = ctx.getObject(TOTALRECEIVED.getBytes());
        byte[]balance = ctx.getObject(BALANCE.getBytes());
        totalRecv = ByteUtils.add(totalRecv,amount);
        balance = ByteUtils.add(balance,amount);
        donateCount = ByteUtils.incrress(donateCount);

        byte[] donateId = "".getBytes();// TODO @fengjin
        byte[]userDonateKey = USERDONATE + ByteUtils.toString(donor)+ ByteUtils.toString("%")+ByteUtils.toString(donatId);
        byte[] allDonateKey = ByteUtils.concat(ALLDONATE,donateId);
        byte[]donateDetail= (
                "donor="+ByteUtils.toString(donor) +
                ",amount=" + ByteUtils.toString(amount) +
                "timestamp=" + ByteUtils.toString(timestamp)+
                "comments = " + ByteUtils.toString(comments)
        ).getBytes();
        ctx.putObject(userDonateKey,donateDetail);
        ctx.putObject(allDonateKey,donateDetail);
        ctx.putObject(DONATECOUNT.getBytes(),donateCount);
        ctx.putObject(TOTALRECEIVED.getBytes(),totalRecv);
        ctx.putObject(BALANCE.getBytes(),balance);
        return Response.ok(donateId);
    }

    @ContractMethod
    public Response cost(Context ctx){
        if (!this.isAdmin(ctx)){
            return Response.error("only admin can add cost record");
        }
        byte[]to = ctx.args().get("to");
        byte[]amount = ctx.args().get("amount");
        byte[] timestamp = ctx.args().get("timestamp");
        byte[] comments = ctx.args().get("comments");

        if (to.length==0 || amount.length == 0|| timestamp.length ==0 || comments.length ==0){
            return Response.error("missing to or amount or timestamp or comments");
        }
        byte[] totalCost = ctx.getObject(TOTALCOSTS.getBytes());
        byte[] balance = ctx.getObject(BALANCE.getBytes());
        byte[]costCount = ctx.getObject(COSTCOUNT.getBytes());
        if (ByteUtils.less(balance,amount)){
            return Response.error("fund balance not enough");
        }
        byte[] costId = "".getBytes(); // TODO @fengjin
        byte[] allCostKey = ByteUtils.concat(ALLCOST,costId);
        byte[] costDetail = (
                    "to="+ByteUtils.toString(to)+
                        ",amount="+ ByteUtils.toString(amount) +
                        ",timestamp="+ByteUtils.toString(timestamp)+
                        ",comments="+ ByteUtils.toString(comments)
                ).getBytes();
        ctx.putObject(allCostKey,costDetail);
        ctx.putObject(COSTCOUNT.getBytes(),costCount);
        ctx.putObject(TOTALCOSTS.getBytes(),totalCost);
        ctx.putObject(BALANCE.getBytes(),balance);
        return Response.error(ByteUtils.toString(costId));
    }

    @ContractMethod
    public Response statistics(Context ctx){
        byte[]totalCost = ctx.getObject(TOTALCOSTS.getBytes());
        byte[]balance = ctx.getObject(BALANCE.getBytes());
        byte[]totalRec = ctx.getObject(TOTALRECEIVED.getBytes());
        byte[]result = (
                "totalDonates=" + ByteUtils.toString(totalRec)+ ","+// TODO
                "totalCosts=" + ByteUtils.toString(totalCost) +","+
                        "fundBalance="+ByteUtils.toString(balance)
                ).getBytes();
        return Response.ok(result);
    }
    @ContractMethod
    public Response queryDonor(Context ctx){
        String donor = ByteUtils.toString(ctx.args().get("donor".getBytes()));
        if (donor.length()==0){
            return Response.error("missing donor argument");
        }
        String userDonateKey = USERDONATE+donor+"%";
        ByteBuffer buf = new ByteBuffer();
        AtomicInteger donateCount = new AtomicInteger(); // TODO @fengjin
        ctx.newIterator(userDonateKey.getBytes(),(userDonateKey+"~").getBytes()).forEachRemaining(
                elem->{
//                    TODO 验证
                    donateCount.getAndIncrement();
                    String donateId = ByteUtils.toString(elem.getKey()).substring(userDonateKey.length());
                    String content = ByteUtils.toString(elem.getValue());
                    buf.appendBytes((
                            "id="+donateId+","+
                                    "content="+content+"\n"
                    ).getBytes());
                }
        );
        return Response.ok(buf.elems); // TODO @fengjin

    }
    @ContractMethod
    public Response queryDonates(Context ctx){
        byte[]startid = ctx.args().get("startid");
        byte[]limit = ctx.args().get("limit");
        if (startid.length==0 || limit.length == 0){
            return Response.error("missing startid or limit");
        }
        if (ByteUtils.less(limit,MAX_LIMIT)){
            return Response.error("limit must be less than"+MAX_LIMIT);
        }
        byte[]donateKey = ByteUtils.concat(ALLDONATE,startid);
        int limit1=0;
        AtomicInteger selected = new AtomicInteger();
        ByteBuffer buf = new ByteBuffer();
        ctx.newIterator(donateKey, ByteUtils.concat(donateKey,"~")).forEachRemaining(
                elem->{
                    if (selected.get() >=limit1) {// 注意边界条件
                        return;
                    }
//                    TODO 检查
                    selected.getAndIncrement();
                    String donateId = ByteUtils.toString(elem.getKey()).substring(ALLDONATE.length());
                    String content = ByteUtils.toString(elem.getValue());
                    buf.appendBytes((
                            "id="+donateId+","+
                                    content+"\n" // TODO
                            ).getBytes());
                }
        );
        return Response.ok(buf.elems);
    }
    @ContractMethod
    public Response queryCosts(Context ctx){
        byte[]startid = ctx.args().get("startid");
        byte[]limit = ctx.args().get("limit");
        if (startid.length==0 || limit.length == 0){
            return Response.error("missing startid or limit");
        }
        if (ByteUtils.less(limit,MAX_LIMIT)){
            return Response.error("limit must be less than"+MAX_LIMIT);
        }

        byte[]donateKey = ByteUtils.concat(ALLCOST,startid);
        int limit1=0; // TODO
        AtomicInteger selected = new AtomicInteger();
        ByteBuffer buf = new ByteBuffer();
//        可以抽象一下下
        ctx.newIterator(donateKey, ByteUtils.concat(donateKey,"~")).forEachRemaining(
                elem->{
                    if (selected.get() >=limit1) {// 注意边界条件
                        return;
                    }
//                    TODO 检查
                    selected.getAndIncrement();
                    String donateId = ByteUtils.toString(elem.getKey()).substring(ALLCOST.length());
                    String content = ByteUtils.toString(elem.getValue());
                    buf.appendBytes((
                            "id="+donateId+","+
                                    content+"\n" // TODO
                    ).getBytes());
                }
        );
        return Response.ok(buf.elems);
    }


    public static void main(String[] args) {
        Driver.serve(new CharityDemo());
    }
}
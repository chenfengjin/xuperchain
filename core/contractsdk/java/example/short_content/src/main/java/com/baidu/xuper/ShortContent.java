package com.baidu.xuper;

import com.sun.tools.javac.util.ByteBuffer;

/**
 * Counter
 */
public class ShortContent implements Contract {
    final String userBucket = "USER";
    final int titleLengthLimit = 100;
    final int contentLengthLimit = 3000;

    @Override
    @ContractMethod
    public Response initialize(Context ctx) {
        return Response.ok("ok".getBytes());
    }

    @ContractMethod
    public Response storeShortContent(Context ctx) {
        ByteBuffer buf = new ByteBuffer();
        byte[]userId = ctx.args().get("user_id");
        byte[] title = ctx.args().get("title");
        byte[]topic =  ctx.args().get("topic");
        byte[]content = ctx.args().get("content");
//        TODO
        buf.appendBytes(userBucket.getBytes());
        buf.appendBytes("/".getBytes());
        buf.appendBytes(userId);
        buf.appendBytes("/".getBytes());
        buf.appendBytes(topic);
        buf.appendBytes("/".getBytes());
        buf.appendBytes(title);
        if( topic.length  > contentLengthLimit||title.length > titleLengthLimit || content.length > contentLengthLimit){
            return Response.error("The length of topic or title or content is more than limitation");
        }
        byte[] userKey = buf.elems;
//        error 处理呢？
        ctx.putObject(userKey,content);
        return Response.ok("ok".getBytes());

    }

    @ContractMethod
    public Response queryByUser(Context ctx){
        String userId = ctx.args().get("user_id").toString();
        ByteBuffer result = new ByteBuffer();
        String start = userBucket + "/"  + "user_id" + "/";
        String end = start + "~";
        ctx.newIterator(start.getBytes(),end.getBytes()).forEachRemaining(
                item ->{
                    result.appendBytes(item.getKey());
                    result.appendBytes("\n".getBytes());
                    result.appendBytes(item.getValue());
                    result.appendBytes("\n".getBytes());
                }
        );
        return Response.ok("ok".getBytes());
    }

    @ContractMethod
    public Response queryByTitle(Context ctx){
        String userId = ctx.args().get("user_id").toString();
        String  title = ctx.args().get("title").toString();
        String topic =  ctx.args().get("topic").toString();
//        String content = ctx.args().get("content").toString();
        String key = userBucket + "/" + userId + "/" + "/" + topic + "/" + title;
        return Response.ok(ctx.getObject(key.getBytes()));
    }

    @ContractMethod
    public Response queryByTopic(Context ctx){
        byte[] userId = ctx.args().get("user_id");
        byte[] topic = ctx.args().get("topic");
        ByteBuffer key = new ByteBuffer();
        key.appendBytes(userBucket.getBytes());
        key.appendBytes("/".getBytes());
        key.appendBytes(userId);
        key.appendBytes("/".getBytes());
        key.appendBytes(topic);
        key.appendBytes("/".getBytes());

        byte[] start = key.elems;
        key.appendBytes("~".getBytes());
        byte []end = key.elems;
        ByteBuffer result = new ByteBuffer();
        ctx.newIterator(start,end).forEachRemaining(
                item ->{
                    result.appendBytes(item.getKey());
                    result.appendBytes("\n".getBytes());
                    result.appendBytes(item.getValue());
                    result.appendBytes("\n".getBytes());
                }
        );
        return Response.ok(result.elems);
    }


    public static void main(String[] args) {
        Driver.serve(new ShortContent());
    }
}
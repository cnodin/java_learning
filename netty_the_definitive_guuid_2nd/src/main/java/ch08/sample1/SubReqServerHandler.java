package ch08.sample1;

import ch08.protobuf.subscribe.SubscribeReq;
import ch08.protobuf.subscribe.SubscribeResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/10
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq)msg;
        if ("spock".equalsIgnoreCase(req.getUsername())) {
            System.out.println("Service accept client subscribe req : [" + req.toString() + "]");

            ctx.writeAndFlush(generateSubscribeResp(req.getSubReqID()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private SubscribeResp generateSubscribeResp(int subReqID) {
        SubscribeResp.Builder builder = SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed, 3 days later, send to the designated address");

        return builder.build();
    }
}

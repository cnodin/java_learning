package ch08.sample1;

import ch08.protobuf.subscribe.SubscribeReq;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAppender;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/11
 * Time: 00:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SubReqClientHandler extends ChannelHandlerAppender {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(generateSubscribeReq(i));
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private SubscribeReq generateSubscribeReq(int i) {
        SubscribeReq.Builder builder = SubscribeReq.newBuilder();
        builder.setSubReqID(i);
        builder.setUsername("spock");
        builder.setProductName("Netty Book For protobuf");

        List<String> address = new ArrayList();
        address.add("Fujian Xiamen");
        address.add("BeiJing ChaoYangMen");
        address.add("ShenZhen HongShuLin");

        builder.addAllAddress(address);

        return builder.build();
    }
}

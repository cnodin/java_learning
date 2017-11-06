package org.pollux.nettylearning.simpleserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 10/1/17
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 * Description: 自定义的消息处理器
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
		System.out.println("异常信息: \r\n" + cause.getMessage());
	}

	/**
	 * 
	 * @param ctx 通道上下文
	 * @param msg		消息数据
	 * @throws Exception
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(new Date() + " 收到数据 " + msg);
	}
}

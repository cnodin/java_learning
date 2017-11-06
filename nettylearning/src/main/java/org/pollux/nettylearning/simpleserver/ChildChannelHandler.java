package org.pollux.nettylearning.simpleserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 10/1/17
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 * Description: 客户端通道处理
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		System.out.println(String.format("The client socket has connected, IP:%s, port:%s",
						socketChannel.localAddress().getHostName(),
						socketChannel.localAddress().getPort()));

		//半包处理，基于换行符
		socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
		//字符串编码
		socketChannel.pipeline().addLast(new StringDecoder());
		//字符串解码
		socketChannel.pipeline().addLast(new StringEncoder());
		//在管道中添加自己的接收数据的方法
		socketChannel.pipeline().addLast(new MyServerHandler());
	}
}

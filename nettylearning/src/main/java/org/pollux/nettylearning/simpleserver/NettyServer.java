package org.pollux.nettylearning.simpleserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 10/1/17
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 * Description: 简单的Netty服务器
 */
public class NettyServer {

	public void bind() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.childHandler(new ChildChannelHandler());

			ChannelFuture channelFuture = b.bind(53060).sync();
			channelFuture.channel().closeFuture().sync();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		NettyServer nettyServer = new NettyServer();
		nettyServer.bind();
	}

}

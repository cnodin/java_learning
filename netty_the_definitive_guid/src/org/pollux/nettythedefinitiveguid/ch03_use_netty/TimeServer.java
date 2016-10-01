package org.pollux.nettythedefinitiveguid.ch03_use_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by pollux on 16/8/29.
 */
public class TimeServer {

  public void bind(int port){
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    ServerBootstrap b = new ServerBootstrap();
    b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
            option(ChannelOption.SO_BACKLOG, 1024).
            childHandler(new TimeServerHandler());


  }

  private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

    }
  }

}

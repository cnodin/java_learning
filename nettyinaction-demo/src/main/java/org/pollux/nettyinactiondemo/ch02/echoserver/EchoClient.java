package org.pollux.nettyinactiondemo.ch02.echoserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author wangbinbin
 * @version EchoClient, 2019/5/16 7:54 wangbinbin Exp
 */
public class EchoClient {

  private final String host;
  private final int port;

  public EchoClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void start() throws InterruptedException {
    EventLoopGroup group = new NioEventLoopGroup();

    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .remoteAddress(new InetSocketAddress(host, port))
            .handler(new ChannelInitializer<SocketChannel>() {
              @Override
              protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new EchoClientHandler());
              }
            });
    ChannelFuture channelFuture = bootstrap.connect().sync();
    channelFuture.channel().closeFuture().sync();
  }

  public static void main(String[] args) throws InterruptedException {
    if (args.length != 2) {
      System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host><port>");
      return;
    }

    String host = args[0];
    int port = Integer.parseInt(args[1]);
    new EchoClient(host, port).start();
  }

}

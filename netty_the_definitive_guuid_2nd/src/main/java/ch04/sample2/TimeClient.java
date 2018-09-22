package ch04.sample2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 2018/6/9
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class TimeClient {

    public void connnect(int port, String host) throws InterruptedException {

        //配置客户端NIO线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new TimeClientHandler());
                    }
                });

        ChannelFuture f = b.connect(host, port).sync();

        //等待客户端链路关闭
        f.channel().closeFuture().sync();
    }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new TimeClient().connnect(port, "127.0.0.1");
    }

}

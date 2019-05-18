package org.pollux.nettyinactiondemo.ch02.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author wangbinbin
 * @version EchoClientHandler, 2019/5/16 8:00 wangbinbin Exp
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {

    Charset utf8 = CharsetUtil.UTF_8;
    ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
    ByteBuf sliced = byteBuf.slice(0, 15);
    System.out.println(sliced.toString(utf8));
    byteBuf.setByte(0, (byte)'J');

    System.out.println(sliced.toString(utf8));
    ctx.writeAndFlush(sliced);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
    System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}

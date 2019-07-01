package com.limbo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    System.out.println("server start reading");

    // if (msg instanceof ByteBuf) {

    System.out.println(((ByteBuf) msg).toString(Charset.forName("UTF-8")));
    // }
  }
}

package com.limbo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    System.out.println("client start reading");
  }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String
    }
}

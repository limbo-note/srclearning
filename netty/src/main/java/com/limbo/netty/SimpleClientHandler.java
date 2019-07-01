package com.limbo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.Delimiters;

import java.nio.charset.Charset;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("client start reading");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connect successfully");

        ctx.writeAndFlush("哈哈");
        Thread.sleep(3000);
        ctx.writeAndFlush("hehe");
        Thread.sleep(3000);
        ctx.writeAndFlush(";");
    }
}

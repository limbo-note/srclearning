package com.limbo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

  public static void main(String[] args) {
    EventLoopGroup group = new NioEventLoopGroup();

    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap
          .channel(NioSocketChannel.class)
          .group(group)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .handler(
              new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                  //                  ch.pipeline()
                  //                      .addLast(
                  //                          new DelimiterBasedFrameDecoder(
                  //                              Integer.MAX_VALUE,
                  // Delimiters.lineDelimiter()[0]));
                  ch.pipeline().addLast(new StringEncoder());
                  ch.pipeline().addLast(new SimpleClientHandler());
                }
              });

      ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
      future.channel().closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }
}

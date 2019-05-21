package com.limbo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

  public static void main(String[] args) {

    EventLoopGroup parentGroup = new NioEventLoopGroup(1);
    EventLoopGroup childGroup = new NioEventLoopGroup(); // 默认为处理器数量*2
    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();

      serverBootstrap
          .group(parentGroup, childGroup)
          .option(ChannelOption.SO_BACKLOG, 128)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .channel(NioServerSocketChannel.class)
          .childHandler(
              new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                  channel.pipeline().addLast(new SimpleServerHandler());
                }
              });

      ChannelFuture future = serverBootstrap.bind(8888).sync();
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      parentGroup.shutdownGracefully();
      childGroup.shutdownGracefully();
    }
  }
}

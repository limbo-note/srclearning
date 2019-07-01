package com.limbo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class PrintableDelimiterBasedFrameDecoder extends DelimiterBasedFrameDecoder {
    public PrintableDelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf delimiter) {
        super(maxFrameLength, delimiter);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        Field field =
                this.getClass().getSuperclass().getSuperclass().getDeclaredField("cumulation");
        field.setAccessible(true);
        ByteBuf buf = (ByteBuf) field.get(this);
        if (buf != null)
            System.out.println("cumulation: " + buf.toString(Charset.forName("UTF-8")));
    }
}

package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteBuffer;
import java.util.List;

public class ServerDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.skipBytes(1);
        int length = in.readByte();
        if (in.readableBytes() < length) {
            return;
        }
        in.resetReaderIndex();
        byte[] data = new byte[in.readableBytes()];
        for(int i = 0; i < in.readableBytes(); i++){
            data[i] = in.getByte(i);
        }
        //ByteBuffer buffer = ByteBuffer.wrap(data);
        String hex = NettyUtil.bytesToHex(data);
        out.add(hex);
    }
}

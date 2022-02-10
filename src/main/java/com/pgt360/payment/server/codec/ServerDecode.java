package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class ServerDecode extends ByteToMessageDecoder {
    int i = 0;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String response = "";
        if(in.readableBytes()>0){
            String respuesta = in.toString(CharsetUtil.UTF_8);
            String hex = NettyUtil.bytesToHex(respuesta.getBytes());
            response = response + hex;
            System.out.println(response);
            out.clear();
            out.add(hex);
        }
    }
}

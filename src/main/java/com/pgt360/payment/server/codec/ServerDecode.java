package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class ServerDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>0){
            String respuesta = in.toString(CharsetUtil.UTF_8);
            String hex = NettyUtil.bytesToHex(respuesta.getBytes());
            if(!(hex.equals("06"))){
                out.add(hex);
            }
            in.resetWriterIndex();
            System.out.println("Mensaje enviado al handler:"+hex);
        }
    }
}

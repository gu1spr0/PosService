package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;

import java.util.List;

@ChannelHandler.Sharable
public class ServerDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>0){
            byte[] data = new byte[in.readableBytes()];
            for(int i = 0; i < in.readableBytes(); i++){
                data[i] = in.getByte(i);
            }
            //ByteBuffer buffer = ByteBuffer.wrap(data);
            String hex = NettyUtil.bytesToHex(data);
            out.add(hex);
            System.out.println("Mensaje decodificado y enviado correctamente");
        }else{
            System.out.println("************No existe bytes legibles!**********");
        }
    }
}

package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.bytes.ByteArrayDecoder;

import java.util.List;

@ChannelHandler.Sharable
public class ServerDecode extends ByteArrayDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("Mensaje de entrada para de codificar:"+in.readInt());
        if(in.readableBytes()>0){
            /*byte[] data = new byte[in.readableBytes()];
            for(int i = 0; i < in.readableBytes(); i++){
                data[i] = in.getByte(i);
            }
            //ByteBuffer buffer = ByteBuffer.wrap(data);
            String hex = NettyUtil.bytesToHex(data);
            out.add(hex);*/
            // copia el contenido de ByteBuf a una matriz de bytes
            // copy the ByteBuf content to a byte array
            byte[] array = new byte[in.readableBytes()];
            in.getBytes(0, array);
            String hex = NettyUtil.bytesToHex(array);
            out.add(array);

            System.out.println("Mensaje decodificado y enviado correctamente");
        }else{
            System.out.println("************No existe bytes legibles!**********");
        }
    }
}

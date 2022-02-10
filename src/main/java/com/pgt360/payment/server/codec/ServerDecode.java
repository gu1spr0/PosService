package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServerDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()>0){
            String respuesta = in.toString(CharsetUtil.UTF_8);
            System.out.println("Tamaño:"+in.readableBytes());
            System.out.println("Cadena respuesta:"+respuesta);
            //String data = codRespuesta.substring(50,codRespuesta.length());
            String hex = NettyUtil.bytesToHex(respuesta.getBytes());
            System.out.println("Respuesta:"+hex);
            ctx.executor().schedule(new Runnable() {
                @Override
                public void run() {
                    int begin = 50;
                    System.out.println("Respuesta del POS:"+NettyUtil.hex2a(hex.substring(begin, begin + 4)));
                }
            },5000,TimeUnit.MILLISECONDS);


        }
        /*if(in.readableBytes()>0){
            String msg = in.toString(CharsetUtil.UTF_8);
            in.readerIndex(in.readerIndex()+in.readableBytes());
            System.out.println("Decode:"+msg);
            //out.add(msg);
        }else{
            System.out.println("No existe bytes para leer");
        }*/

        /*if(in.readableBytes()>0){
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
        }*/
    }
}

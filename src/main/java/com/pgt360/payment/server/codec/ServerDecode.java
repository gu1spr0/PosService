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
        System.out.println("Esperando..");
        ctx.executor().schedule(() -> System.out.println("..."),5, TimeUnit.SECONDS);
        System.out.println("Reading..");
        if(in.readableBytes()>0){
            String msg = in.toString(CharsetUtil.UTF_8);
            in.readerIndex(in.readerIndex()+in.readableBytes());
            System.out.println("Decode:"+msg);
            //out.add(msg);
        }else{
            System.out.println("No existe bytes para leer");
        }

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

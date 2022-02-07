package com.pgt360.payment.server.codec;


import com.pgt360.payment.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;


import java.util.List;

public class ServerDecode extends DelimiterBasedFrameDecoder {
    public ServerDecode(int maxFrameLength, boolean stripDelimiter, ByteBuf delimiter){
        super(maxFrameLength, stripDelimiter, delimiter);
        this.setSingleDecode(true);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        Object frame = super.decode(ctx, buffer);
        ByteBuf bufFrame = null;
        if(frame instanceof ByteBuf){
            bufFrame = (ByteBuf)frame;
        } else {
            System.out.println("OBJECT TYPE: "+frame.getClass().getSimpleName());
        }
        byte lrc = buffer.readByte();
        bufFrame.writeByte(lrc);
        return bufFrame;
    }

    public ServerDecode(int maxFrameLength, ByteBuf delimiter){
        super(maxFrameLength, delimiter);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("VERIFONE DECODER READY");
    }
    /*@Override
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
    }*/
}

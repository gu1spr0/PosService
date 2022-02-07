package com.pgt360.payment.server.config;

import com.pgt360.payment.server.codec.ServerDecode;
import com.pgt360.payment.server.handler.ServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    ByteBuf delimiter;
    byte[] STX = {0x02};
    byte[] ETX = {0x03};
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        setupDelimiter();
        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024*1024, delimiter));
        socketChannel.pipeline().addLast(new ServerDecode());
        socketChannel.pipeline().addLast(new ServerHandler());
    }
    private void setupDelimiter(){
        delimiter = Unpooled.copiedBuffer(ETX);
    }
}

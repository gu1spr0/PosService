package com.pgt360.payment.server.config;

import com.pgt360.payment.server.codec.ServerDecode;
import com.pgt360.payment.server.handler.ServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    ByteBuf delimiter;
    byte[] STX = {0x02};
    byte[] ETX = {0x03};

    /*private final ServerHandler serverHandler = new ServerHandler();
    private final StringDecoder stringDecoder = new StringDecoder();*/
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        setupDelimiter();
        socketChannel.pipeline().addLast(new ServerDecode(65*1024, false, delimiter));
        socketChannel.pipeline().addLast(new ByteArrayDecoder());
        socketChannel.pipeline().addLast(new ByteArrayEncoder());
        socketChannel.pipeline().addLast(new ServerHandler());
        /*ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024 * 1024, Delimiters.lineDelimiter()));
        pipeline.addLast(stringDecoder);
        pipeline.addLast(serverHandler);*/
    }
    private void setupDelimiter(){
        delimiter = Unpooled.copiedBuffer(ETX);
    }
}

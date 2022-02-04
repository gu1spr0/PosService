package com.pgt360.payment.server.config;

import com.pgt360.payment.server.codec.ServerDecode;
import com.pgt360.payment.server.handler.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    private final ServerDecode serverDecode = new ServerDecode();
    private final ServerHandler serverHandler = new ServerHandler();
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(serverDecode);
        pipeline.addLast(serverHandler);
    }
}

package com.pgt360.payment.server.config;

import com.pgt360.payment.server.codec.ServerDecode;
import com.pgt360.payment.server.handler.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel){
        socketChannel.pipeline().addLast(new ServerDecode(), new ReadTimeoutHandler(60));
        socketChannel.pipeline().addLast(new ServerHandler());
    }
}

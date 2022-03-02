package com.pgt360.payment.server.config;

import com.pgt360.payment.server.codec.ServerDecode;
import com.pgt360.payment.server.handler.ServerHandler;
import com.pgt360.payment.service.ConexionService;
import com.pgt360.payment.service.DispositivoService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private final ConexionService conexionService;

    @Autowired
    private final DispositivoService dispositivoService;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ServerDecode());
        socketChannel.pipeline().addLast(new ServerHandler(conexionService, dispositivoService));
    }
}

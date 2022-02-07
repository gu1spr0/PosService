package com.pgt360.payment.server.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ServerProperties.class)
public class ServerConfiguration {
    private final ServerProperties serverProperties;
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap(ServerInitializer serverInitializer) {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossLoopGroup(), workerLoopGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(serverInitializer);
        return b;
    }
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossLoopGroup() {
        return new NioEventLoopGroup(1);
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerLoopGroup() {
        return new NioEventLoopGroup(10);
    }
    @Bean
    public InetSocketAddress tcpSocketAddress() {
        return new InetSocketAddress(serverProperties.getPort());
    }
}

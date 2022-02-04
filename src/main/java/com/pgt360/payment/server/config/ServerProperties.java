package com.pgt360.payment.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ConfigurationProperties(prefix = "netty")
public class ServerProperties {
    @NotNull
    @Size(min = 1000, max = 65535)
    private int port;
}

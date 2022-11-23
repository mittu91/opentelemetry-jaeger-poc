package com.demo.project2.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class GatewayRequestLogger implements WebFilter {

    private final Logger log = LoggerFactory.getLogger(GatewayRequestLogger.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (!exchange.getRequest().getURI().toString().contains("/actuator/health")) {
            log.debug("Path: {}, Method: {} ", exchange.getRequest().getPath(), exchange.getRequest().getMethod());
        }
        return chain.filter(exchange);
    }
}

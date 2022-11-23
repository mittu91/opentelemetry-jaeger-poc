package com.demo.project2.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class SecurityContextRepository  implements ServerSecurityContextRepository {

    private final ReactiveAuthenticationManager authenticationManager;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public SecurityContextRepository(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new RuntimeException("Operation not supported.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        log.debug("Secured Path: {}", request.getPath());
        String authHeader = null;
        if (StringUtils.isNotBlank(request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))) {
            authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        } else if (StringUtils.isNotBlank(request.getQueryParams().getFirst(HttpHeaders.AUTHORIZATION))) {
            authHeader = request.getQueryParams().getFirst(HttpHeaders.AUTHORIZATION);
        }

        if (StringUtils.isNotBlank(authHeader)) {
            Authentication auth = new UsernamePasswordAuthenticationToken(authHeader, authHeader);
            return authenticationManager.authenticate(auth)
                    .map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }
}

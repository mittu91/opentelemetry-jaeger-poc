package com.demo.project2.security;

import com.demo.project2.constant.PathSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebFluxSecurity
@RefreshScope
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ReactiveAuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final PathSettings pathSettings;

    public SecurityConfig(ReactiveAuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository, PathSettings pathSettings) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
        this.pathSettings = pathSettings;
    }

    @Bean
    @RefreshScope
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        log.info("Configuring Security endpoints");
        http
                .csrf().disable()
                .exceptionHandling().and()
                .httpBasic().disable()
                .formLogin().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(pathSettings.getPrivatePath()).denyAll()
                .pathMatchers(pathSettings.getAuthenticatedPath()).authenticated()
                .pathMatchers(pathSettings.getPublicPath()).permitAll();
        return http.authorizeExchange().anyExchange().denyAll().and().build();
    }
}

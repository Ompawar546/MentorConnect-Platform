package com.mentorconnect.apigateway.security;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.web.server.WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        try {

            String jwt = authHeader.substring(7);

            if (jwtService.isTokenValid(jwt)) {

            	String email = jwtService.extractUsername(jwt);
            	String role = jwtService.extractRole(jwt);
            	Long userId = jwtService.extractUserId(jwt);

            	// Remove any client-supplied header and add our trusted one
            	ServerHttpRequest mutatedRequest = request.mutate()
            			.headers(headers -> {
            			    headers.remove("X-User-Id");
            			    headers.remove("X-User-Role");

            			    headers.add("X-User-Id", String.valueOf(userId));
            			    headers.add("X-User-Role", role);
            			})
            	        .build();

            	ServerWebExchange mutatedExchange = exchange.mutate()
            	        .request(mutatedRequest)
            	        .build();

            	UsernamePasswordAuthenticationToken authentication =
            	        new UsernamePasswordAuthenticationToken(
            	                email,
            	                null,
            	                List.of(new SimpleGrantedAuthority("ROLE_" + role)));

            	return chain.filter(mutatedExchange)
            	        .contextWrite(
            	                ReactiveSecurityContextHolder.withSecurityContext(
            	                        Mono.just(new SecurityContextImpl(authentication))));
            }

        } catch (JwtException | IllegalArgumentException ex) {
            // Invalid token, continue without authentication
        }

        return chain.filter(exchange);
    }
}
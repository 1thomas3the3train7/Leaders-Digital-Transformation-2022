package com.example.gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Route {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(r -> r
                        .path("/test")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/user/admin")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/user/auth")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/user/getrefreshtoken")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/user/register")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/user/users/login")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/idea/save")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/team/create")
                        .uri("lb://authservice"))
                .route(r -> r
                        .path("/project/create")
                        .uri("lb://authservice"))
                .build();
    }
}

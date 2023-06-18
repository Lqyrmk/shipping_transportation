package com.lqyrmk.transportation.config;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("path_route", r -> r.path("/get").uri("http://httpbin.org"))
            .build();
    }
}

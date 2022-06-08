package com.parceldelivery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Router {

    @Value("${service.auth}")
    String auth;

    @Value("${service.query}")
    String query;

    @Value("${service.shipment}")
    String shipment;

    @Value("${service.order}")
    String order;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("auth", r -> r.path("/auth/**").uri(auth + ":8081/auth"))
                .route("user", r -> r.path("/user/**").uri(auth + ":8081/user"))
                .route("query", r -> r.path("/query/**").uri(query + ":8084/query"))
                .route("shipment", r -> r.path("/shipment/**").uri(shipment + ":8083/shipment"))
                .route("order", r -> r.path("/order/**").uri(order + ":8082/order"))
                .build();
    }
}

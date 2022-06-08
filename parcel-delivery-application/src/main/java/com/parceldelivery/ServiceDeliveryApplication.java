package com.parceldelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServiceDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeliveryApplication.class, args);
    }


}

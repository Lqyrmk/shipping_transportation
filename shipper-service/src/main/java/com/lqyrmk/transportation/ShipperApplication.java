package com.lqyrmk.transportation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableHystrix
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ShipperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipperApplication.class, args);
    }

}

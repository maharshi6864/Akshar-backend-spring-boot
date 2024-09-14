package com.aksharspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AksharSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AksharSpringBootApplication.class, args);
    }

}

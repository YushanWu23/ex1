package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class Main_30000 {
    public static void main(String[] args) {
        SpringApplication.run(Main_30000.class,args);
    }
}
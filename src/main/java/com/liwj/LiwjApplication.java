package com.liwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class LiwjApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiwjApplication.class, args);

    }
}

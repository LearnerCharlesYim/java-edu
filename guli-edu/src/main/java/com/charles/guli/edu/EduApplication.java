package com.charles.guli.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackages = {"com.charles.log.repository","com.charles.guli.edu.repository"})
@ComponentScan("com.charles")
public class EduApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EduApplication.class, args);
        System.out.println(context.getClass().getSimpleName());
    }
}

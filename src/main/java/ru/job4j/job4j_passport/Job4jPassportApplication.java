package ru.job4j.job4j_passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Job4jPassportApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jPassportApplication.class, args);
    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
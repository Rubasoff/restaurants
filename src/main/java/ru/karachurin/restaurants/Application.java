package ru.karachurin.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Денис on 02.11.2016.
 */
@SpringBootApplication
@PropertySource(value="classpath:application")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.example.conferencesimulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConferenceSimulationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConferenceSimulationApplication.class, args);
        Object x = context.getBean("dataSource");
        System.out.println(x);
    }

}

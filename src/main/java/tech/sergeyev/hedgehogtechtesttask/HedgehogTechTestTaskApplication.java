package tech.sergeyev.hedgehogtechtesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HedgehogTechTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HedgehogTechTestTaskApplication.class, args);
    }

}

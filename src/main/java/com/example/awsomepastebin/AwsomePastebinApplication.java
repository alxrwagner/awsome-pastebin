package com.example.awsomepastebin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AwsomePastebinApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsomePastebinApplication.class, args);
    }

}

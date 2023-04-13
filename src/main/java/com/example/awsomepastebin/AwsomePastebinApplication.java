package com.example.awsomepastebin;

import com.example.awsomepastebin.controller.PastController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AwsomePastebinApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsomePastebinApplication.class, args);
    }

}

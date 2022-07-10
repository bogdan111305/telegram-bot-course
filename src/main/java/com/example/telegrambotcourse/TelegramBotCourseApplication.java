package com.example.telegrambotcourse;

import com.example.telegrambotcourse.config.TelegramBotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {TelegramBotConfig.class})
public class TelegramBotCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotCourseApplication.class, args);
    }

}

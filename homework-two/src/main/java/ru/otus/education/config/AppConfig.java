package ru.otus.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    AppSettings getAppSettings() {
        return new AppSettings(true);
    }
}

package ru.otus.education.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.education.service.menu.MenuOption;

@Configuration
public class MenuOptionCollectionConfig {
    @Value("${menu.option.user}")
    private String userInfo;
    @Value("${menu.option.start}")
    private String startTest;
    @Value("${menu.option.result}")
    private String quizResult;
    @Value("${menu.option.exit}")
    private String exit;

    @Bean(name = "userInfoMenuOption")
    public MenuOption userInfoMenuOption() {
        return new MenuOption(1, userInfo);
    }

    @Bean(name = "startTestMenuOption")
    public MenuOption startTestMenuOption() {
        return new MenuOption(2, startTest);
    }

    @Bean(name = "quizResult")
    public MenuOption quizResultMenuOption() {
        return new MenuOption(3, quizResult);
    }

    @Bean(name = "exitMenuOption")
    public MenuOption stopApplicationMenuOption() {
        return new MenuOption(4, exit);
    }
}

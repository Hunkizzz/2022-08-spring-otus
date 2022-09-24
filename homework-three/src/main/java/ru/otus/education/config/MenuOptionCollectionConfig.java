package ru.otus.education.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.education.service.InternationalService;
import ru.otus.education.service.menu.MenuOption;

@Configuration
@RequiredArgsConstructor
public class MenuOptionCollectionConfig {
    private final InternationalService internationalService;

    @Bean(name = "userInfoMenuOption")
    public MenuOption userInfoMenuOption() {
        return new MenuOption(1, internationalService.getMessage("menu.option.user", null));
    }

    @Bean(name = "startTestMenuOption")
    public MenuOption startTestMenuOption() {
        return new MenuOption(2, internationalService.getMessage("menu.option.start", null));
    }

    @Bean(name = "quizResult")
    public MenuOption quizResultMenuOption() {
        return new MenuOption(3, internationalService.getMessage("menu.option.result", null));
    }

    @Bean(name = "exitMenuOption")
    public MenuOption stopApplicationMenuOption() {
        return new MenuOption(4, internationalService.getMessage("menu.option.exit", null));
    }
}

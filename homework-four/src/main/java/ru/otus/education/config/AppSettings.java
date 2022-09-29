package ru.otus.education.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSettings implements ApplicationStopServiceSettingsProvider {
    private final boolean confirmExit;

    public AppSettings() {
        this.confirmExit = true;
    }

    @Override
    public boolean isConfirmExit() {
        return confirmExit;
    }


}

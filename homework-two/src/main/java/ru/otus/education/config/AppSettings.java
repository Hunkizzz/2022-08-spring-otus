package ru.otus.education.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppSettings implements ApplicationStopServiceSettingsProvider {
    private final boolean confirmExit;

    @Override
    public boolean isConfirmExit() {
        return confirmExit;
    }


}

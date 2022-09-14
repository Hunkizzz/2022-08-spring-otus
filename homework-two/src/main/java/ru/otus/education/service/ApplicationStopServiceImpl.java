package ru.otus.education.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.education.config.ApplicationStopServiceSettingsProvider;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class ApplicationStopServiceImpl implements ApplicationStopService {

    private final ApplicationStopServiceSettingsProvider settingsProvider;
    private final AtomicBoolean executionFlag;

    public ApplicationStopServiceImpl(
            ApplicationStopServiceSettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
        this.executionFlag = new AtomicBoolean(true);
    }

    @Override
    public boolean isApplicationRunning() {
        return executionFlag.get();
    }

    @Override
    public void stopApplication() {
        Scanner scanner = new Scanner(System.in);
        if (settingsProvider.isConfirmExit()) {
            log.info("Do you want exit? (yes/no)");
            var exitConfirmation = scanner.nextLine();
            if (exitConfirmation.equalsIgnoreCase("no")) {
                return;
            }
        }
        executionFlag.set(false);
    }
}

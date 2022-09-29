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
    private final InternationalService internationalService;

    public ApplicationStopServiceImpl(ApplicationStopServiceSettingsProvider settingsProvider
            , InternationalService internationalService) {
        this.settingsProvider = settingsProvider;
        this.executionFlag = new AtomicBoolean(true);
        this.internationalService = internationalService;
    }

    @Override
    public boolean isApplicationRunning() {
        return executionFlag.get();
    }

    @Override
    public void stopApplication() {
        Scanner scanner = new Scanner(System.in);
        if (settingsProvider.isConfirmExit()) {
            log.info(internationalService.getMessage("exam.exit", null));
            var exitConfirmation = scanner.nextLine();
            if (exitConfirmation.equalsIgnoreCase("no") || exitConfirmation.equalsIgnoreCase("нет")) {
                return;
            }
        }
        executionFlag.set(false);
    }
}

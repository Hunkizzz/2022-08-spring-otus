package ru.otus.education.service.processors;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.education.service.ApplicationStopService;
import ru.otus.education.service.menu.MenuOption;

@Service
@Slf4j
public class StopApplicationSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final ApplicationStopService applicationStopService;

    public StopApplicationSingleCommandProcessor(ApplicationStopService applicationStopService,
                                                 @Qualifier("exitMenuOption") @NonNull MenuOption processedCommandOption) {
        this.applicationStopService = applicationStopService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        applicationStopService.stopApplication();
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}

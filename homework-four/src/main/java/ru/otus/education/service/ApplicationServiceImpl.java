package ru.otus.education.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import ru.otus.education.exceptions.MenuCommandProcessorNotFound;
import ru.otus.education.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.menu.MenuOptionsRegistry;
import ru.otus.education.service.processors.MenuCommandsProcessor;

import java.util.Comparator;
import java.util.Scanner;

@Slf4j
@Service
@ConditionalOnProperty(value = "spring.shell.interactive.enabled", havingValue = "false", matchIfMissing = true)
public class ApplicationServiceImpl implements ApplicationService, ApplicationRunner {
    private final MenuOptionsRegistry menuOptionsRegistry;
    private final MenuCommandsProcessor menuCommandsProcessor;
    private final ApplicationStopService applicationStopService;
    private final InternationalService internationalService;

    public ApplicationServiceImpl(MenuOptionsRegistry menuOptionsRegistry
            , MenuCommandsProcessor menuCommandsProcessor
            , ApplicationStopService applicationStopService
            , InternationalService internationalService) {
        this.menuOptionsRegistry = menuOptionsRegistry;
        this.menuCommandsProcessor = menuCommandsProcessor;
        this.applicationStopService = applicationStopService;
        this.internationalService = internationalService;
    }

    @Override
    public void run(ApplicationArguments args) {
        run();
    }

    @Override
    public void run() {
        while (applicationStopService.isApplicationRunning()) {
            Scanner scanner = new Scanner(System.in);
            outputMenu();
            try {
                var selectedMenuItem = readSelectedOptionNumber(scanner);
                processMenuCommand(selectedMenuItem);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(internationalService.getMessage("exception.wrong-number", null));
            } catch (MenuItemIndexOutOfBoundsException e) {
                throw new MenuItemIndexOutOfBoundsException(internationalService.getMessage("exception.wrong-option", null));
            } catch (MenuCommandProcessorNotFound e) {
                throw new MenuCommandProcessorNotFound(internationalService.getMessage("exception.wrong-handler", null));
            }
        }
    }

    @Override
    public void processMenuCommand(int selectedMenuItemId) {
        if (selectedMenuItemId > menuOptionsRegistry.getAvailableMenuOptions().size())
            throw new MenuItemIndexOutOfBoundsException("Given menu item index is out of range");
        var selectedMenuOption = menuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
                .orElseThrow(() -> new MenuCommandProcessorNotFound("Given menu item index is not present in menu"));
        menuCommandsProcessor.processMenuCommand(selectedMenuOption);
    }

    @Override
    public void outputMenu() {
        log.info(internationalService.getMessage("menu.choose-option", null));
        menuOptionsRegistry.getAvailableMenuOptions().stream()
                .sorted(Comparator.comparingInt(MenuOption::getId))
                .map(m -> m.getId() + ". " + m.getDescription())
                .forEach(log::info);
    }

    @Override
    public int readSelectedOptionNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }
}

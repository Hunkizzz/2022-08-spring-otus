package ru.otus.education.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import ru.otus.education.exceptions.MenuCommandProcessorNotFound;
import ru.otus.education.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.menu.MenuOptionsRegistry;
import ru.otus.education.service.processors.MenuCommandsProcessor;

import java.util.Comparator;
import java.util.Scanner;

@AllArgsConstructor
@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final MenuOptionsRegistry menuOptionsRegistry;
    private final MenuCommandsProcessor menuCommandsProcessor;
    private final ApplicationStopService applicationStopService;
    private final InternationalService internationalService;

    @Override
    public void run(ApplicationArguments args) {
        while (applicationStopService.isApplicationRunning()) {
            Scanner scanner = new Scanner(System.in);
            outputMenu();
            try {
                var selectedMenuItem = readSelectedOptionNumber(scanner);
                processMenuCommand(selectedMenuItem);
            } catch (NumberFormatException e) {
                log.info(internationalService.getMessage("exception.wrong-number", null));
            } catch (MenuItemIndexOutOfBoundsException e) {
                log.info(internationalService.getMessage("exception.wrong-option", null));
            } catch (MenuCommandProcessorNotFound e) {
                log.info(internationalService.getMessage("exception.wrong-handler", null));
            }
        }
    }

    @Override
    public void processMenuCommand(int selectedMenuItemId) {
        var selectedMenuOption = menuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
                .orElseThrow(() -> new MenuItemIndexOutOfBoundsException("Given menu item index is out of range"));
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

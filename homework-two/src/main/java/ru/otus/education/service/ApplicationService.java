package ru.otus.education.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ApplicationService {
    private final MenuOptionsRegistry menuOptionsRegistry;
    private final MenuCommandsProcessor menuCommandsProcessor;
    private final ApplicationStopService applicationStopService;

    public void run() {
        while (applicationStopService.isApplicationRunning()) {
            Scanner scanner = new Scanner(System.in);
            outputMenu();
            try {
                var selectedMenuItem = readSelectedOptionNumber(scanner);
                processMenuCommand(selectedMenuItem);
            } catch (NumberFormatException e) {
                log.info("Ошибка при вводе числа");
            } catch (MenuItemIndexOutOfBoundsException e) {
                log.info("Введен неверный номер опции");
            } catch (MenuCommandProcessorNotFound e) {
                log.info("Не найден обработчик для выбранного пункта меню");
            }
        }
    }

    private void processMenuCommand(int selectedMenuItemId) {
        var selectedMenuOption = menuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
                .orElseThrow(() -> new MenuItemIndexOutOfBoundsException("Given menu item index is out of range"));
        menuCommandsProcessor.processMenuCommand(selectedMenuOption);
    }

    private void outputMenu() {
        log.info("please choose one of the options...");
        menuOptionsRegistry.getAvailableMenuOptions().stream()
                .sorted(Comparator.comparingInt(MenuOption::getId))
                .map(m -> m.getId() + ". " + m.getDescription())
                .forEach(log::info);
    }

    private int readSelectedOptionNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }
}

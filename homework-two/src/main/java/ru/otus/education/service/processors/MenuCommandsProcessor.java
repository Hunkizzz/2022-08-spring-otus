package ru.otus.education.service.processors;


import ru.otus.education.service.menu.MenuOption;

public interface MenuCommandsProcessor {
    void processMenuCommand(MenuOption selectedMenuOption);
}

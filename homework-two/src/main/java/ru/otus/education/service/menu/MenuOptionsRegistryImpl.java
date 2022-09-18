package ru.otus.education.service.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuOptionsRegistryImpl implements MenuOptionsRegistry {
    private final List<MenuOption> options;

    @Override
    public List<MenuOption> getAvailableMenuOptions() {
        return options;
    }

    @Override
    public Optional<MenuOption> getMenuOptionById(int id) {
        return options.stream().filter(menuOption -> menuOption.getId() == id).findFirst();
    }
}

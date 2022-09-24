package ru.otus.education.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Scanner;

public interface ApplicationService extends ApplicationRunner {
    @Override
    void run(ApplicationArguments args);

    void processMenuCommand(int selectedMenuItemId);

    void outputMenu();

    int readSelectedOptionNumber(Scanner scanner);
}

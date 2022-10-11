package ru.otus.education.service;

import java.util.Scanner;

public interface ApplicationService {
    void run();

    void processMenuCommand(int selectedMenuItemId);

    void outputMenu();

    int readSelectedOptionNumber(Scanner scanner);
}

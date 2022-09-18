package ru.otus.education.service.processors;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.education.model.User;
import ru.otus.education.service.QuizService;
import ru.otus.education.service.menu.MenuOption;

import java.util.Scanner;

@Service
@Slf4j
public class StudentInfoProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final QuizService quizService;

    public StudentInfoProcessor(@Qualifier("userInfoMenuOption") @NonNull MenuOption processedCommandOption,
                                QuizService quizService) {
        this.processedCommandOption = processedCommandOption;
        this.quizService = quizService;
    }

    @Override
    public void processCommand() {
        Scanner scanner = new Scanner(System.in);
        log.info("Enter surname");
        String surname = scanner.nextLine();
        log.info("Enter name");
        String name = scanner.nextLine();
        quizService.setUser(new User(name, surname));
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}

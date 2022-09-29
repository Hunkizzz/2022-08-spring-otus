package ru.otus.education.service.processors;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.education.service.QuizService;
import ru.otus.education.service.menu.MenuOption;

@Slf4j
@Service
public class QuizProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final QuizService quizService;

    public QuizProcessor(@Qualifier("startTestMenuOption") @NonNull MenuOption processedCommandOption,
                         QuizService quizService) {
        this.processedCommandOption = processedCommandOption;
        this.quizService = quizService;
    }

    @Override
    public void processCommand() {
        quizService.passQuiz();
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}

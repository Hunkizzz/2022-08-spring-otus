package ru.otus.education.service.processors;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.education.service.QuizService;
import ru.otus.education.service.menu.MenuOption;

@Service
@Slf4j
public class ResultQuizProcessor implements MenuSingleCommandProcessor {

    private final MenuOption processedCommandOption;
    private final QuizService quizService;

    public ResultQuizProcessor(@Qualifier("quizResult") @NonNull MenuOption processedCommandOption,
                               QuizService quizService) {
        this.processedCommandOption = processedCommandOption;
        this.quizService = quizService;
    }

    @Override
    public void processCommand() {
        quizService.getResult();
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}

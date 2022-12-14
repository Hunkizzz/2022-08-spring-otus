package ru.otus.education.service.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.education.model.CsvBean;
import ru.otus.education.model.CsvTransfer;
import ru.otus.education.model.Quiz;
import ru.otus.education.model.ResultQuiz;
import ru.otus.education.model.User;
import ru.otus.education.service.CsvService;
import ru.otus.education.service.InternationalService;
import ru.otus.education.service.QuizService;

import java.util.List;
import java.util.Scanner;

@ConditionalOnProperty(value = "spring.shell.interactive.enabled", havingValue = "true", matchIfMissing = true)
@Primary
@ShellComponent("Quiz")
@Slf4j
public class ShellQuizServiceImpl implements QuizService {
    private final ResultQuiz resultQuiz;
    private final CsvService csvService;
    private final InternationalService internationalService;

    public ShellQuizServiceImpl(InternationalService internationalService,
                                CsvService csvService) {
        this.resultQuiz = new ResultQuiz();
        this.csvService = csvService;
        this.internationalService = internationalService;
    }

    @Override
    @ShellMethod(value = "Set user", key = {"user", "u"})
    public void setUser(@ShellOption String name, @ShellOption String surname) {
        User user = new User(name, surname);
        resultQuiz.setUser(user);
        resultQuiz.setUserDataSet(true);
        log.info(internationalService.getMessage("exam.user-info-after-set", new String[]{user.getName(), user.getSurname()}));
    }

    @ShellMethod(value = "Pass quiz", key = {"pass", "p"})
    public void passQuiz() {
        CsvTransfer csvTransfer = csvService.getCsvTransfer();
        Scanner scanner = new Scanner(System.in);
        if (resultQuiz.isUserDataSet() && !resultQuiz.isDone()) {
            log.info(internationalService.getMessage("exam.enter-correct-answer", null));
            int rightAnswerCounter = 0;
            List<CsvBean> csvList = csvTransfer.getCsvList();
            for (int i = 1; i < csvList.size(); i++) {
                CsvBean csvBean = csvList.get(i);
                if (csvBean instanceof Quiz) {
                    log.info("{}\n", ((Quiz) csvBean).getQuestion());
                    log.info("{}\n{}\n{}\n{}\n", ((Quiz) csvBean).getAnswerOptionOne()
                            , ((Quiz) csvBean).getAnswerOptionTwo()
                            , ((Quiz) csvBean).getAnswerOptionThree(),
                            ((Quiz) csvBean).getAnswerOptionFour());
                    int answer = scanner.nextInt();
                    if (answer == Integer.parseInt(((Quiz) csvBean).getRightAnswer())) {
                        rightAnswerCounter++;
                    }
                }
            }
            resultQuiz.setDone(true);
            resultQuiz.setRightAnswers(rightAnswerCounter);
            if (rightAnswerCounter > 3) {
                resultQuiz.setPassed(true);
            }
        } else if (resultQuiz.isDone()) {
            log.info(internationalService.getMessage("exam.retry-message", null));
        } else {
            log.info(internationalService.getMessage("exam.user-info-error", null));
        }
    }

    @ShellMethod(value = "Get result", key = {"result", "r"})
    public ResultQuiz getResult() {
        if (resultQuiz.isPassed()) {
            log.info(internationalService.getMessage("exam.print-score", new String[]{String.valueOf(resultQuiz.getRightAnswers())}));
        } else if (resultQuiz.isDone()) {
            log.info(internationalService.getMessage("exam.fail", null));
        } else {
            log.info(internationalService.getMessage("exam.quiz-not-passed", null));
        }
        return resultQuiz;
    }
}

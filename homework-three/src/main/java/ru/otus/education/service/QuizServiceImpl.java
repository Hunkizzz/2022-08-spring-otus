package ru.otus.education.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.education.model.CsvBean;
import ru.otus.education.model.CsvTransfer;
import ru.otus.education.model.Quiz;
import ru.otus.education.model.ResultQuiz;
import ru.otus.education.model.User;

import java.util.List;
import java.util.Scanner;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final ResultQuiz resultQuiz;
    private final CsvTransfer csvTransfer;
    private final InternationalService internationalService;

    public QuizServiceImpl(CsvTransfer csvTransfer, InternationalService internationalService) {
        this.resultQuiz = new ResultQuiz();
        this.csvTransfer = csvTransfer;
        this.internationalService = internationalService;
    }

    @Override
    public void setUser(User user) {
        resultQuiz.setUser(user);
        resultQuiz.setUserDataSet(true);
        log.info(internationalService.getMessage("exam.user-info-after-set", new String[]{user.getName(), user.getSurname()}));
    }

    @Override
    public void passQuiz() {
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

    @Override
    public void getResult() {
        if (resultQuiz.isPassed()) {
            log.info(internationalService.getMessage("exam.print-score", new String[]{String.valueOf(resultQuiz.getRightAnswers())}));
        } else if (resultQuiz.isDone()) {
            log.info(internationalService.getMessage("exam.fail", null));
        } else {
            log.info(internationalService.getMessage("exam.quiz-not-passed", null));
        }
    }
}

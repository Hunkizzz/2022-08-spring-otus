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

    public QuizServiceImpl(CsvTransfer csvTransfer) {
        this.resultQuiz = new ResultQuiz();
        this.csvTransfer = csvTransfer;
    }

    @Override
    public void setUser(User user) {
        resultQuiz.setUser(user);
        resultQuiz.setUserDataSet(true);
        log.info("Your name {} and surname {} have been set", user.getName(), user.getSurname());
    }

    @Override
    public void passQuiz() {
        Scanner scanner = new Scanner(System.in);
        if (resultQuiz.isUserDataSet() && !resultQuiz.isDone()) {
            log.info("Please, enter number of the right answer");
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
            log.info("You`ve passed the test already\nPlease take your result");
        } else {
            log.info("Please set your fio before test");
        }
    }

    @Override
    public void getResult() {
        if (resultQuiz.isPassed()) {
            log.info("Conratulations\n You`ve passed the test\nYour score is {}", resultQuiz.getRightAnswers());
        } else if (resultQuiz.isDone()) {
            log.info("Sorry. You aren`t passed the test\n Please do it again");
        } else {
            log.info("Please pass the test");
        }
    }
}

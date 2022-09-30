package ru.otus.education.service;

import ru.otus.education.model.ResultQuiz;
import ru.otus.education.model.User;

public interface QuizService {
    void setUser(User user);

    void passQuiz();

    ResultQuiz getResult();
}

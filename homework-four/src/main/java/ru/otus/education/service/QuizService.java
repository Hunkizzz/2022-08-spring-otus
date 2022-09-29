package ru.otus.education.service;

import org.apache.commons.lang3.NotImplementedException;
import ru.otus.education.model.ResultQuiz;
import ru.otus.education.model.User;

public interface QuizService {
    default void setUser(User user) {
        throw new NotImplementedException();
    }

    default void setUser(String name, String surname) {
        throw new NotImplementedException();
    }

    void passQuiz();

    ResultQuiz getResult();
}

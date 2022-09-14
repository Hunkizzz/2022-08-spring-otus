package ru.otus.education.service;

import ru.otus.education.model.User;

public interface QuizService {
    void setUser(User user);

    void passQuiz();

    void getResult();
}

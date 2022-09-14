package ru.otus.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultQuiz {
    User user;
    boolean passed;
    int rightAnswers;
    boolean userDataSet;
    boolean done;
}

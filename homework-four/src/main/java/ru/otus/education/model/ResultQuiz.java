package ru.otus.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultQuiz {
    private User user;
    private boolean passed;
    private int rightAnswers;
    private boolean userDataSet;
    private boolean done;
}

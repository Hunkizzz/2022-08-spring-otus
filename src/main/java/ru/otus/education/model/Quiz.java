package ru.otus.education.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz extends CsvBean {
    @CsvBindByPosition(position = 0)
    private String question;

    @CsvBindByPosition(position = 1)
    private String answerOptionOne;

    @CsvBindByPosition(position = 2)
    private String answerOptionTwo;

    @CsvBindByPosition(position = 3)
    private String answerOptionThree;

    @CsvBindByPosition(position = 4)
    private String answerOptionFour;

    @CsvBindByPosition(position = 5)
    private String rightAnswer;


}

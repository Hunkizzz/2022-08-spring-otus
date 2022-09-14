package ru.otus.education;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.education.model.CsvBean;
import ru.otus.education.service.CsvService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        CsvService csvService = context.getBean(CsvService.class);
        List<CsvBean> quizList = csvService.readDataFromCsv();
        quizList.forEach(System.out::println);
    }
}

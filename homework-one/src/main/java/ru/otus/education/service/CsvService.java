package ru.otus.education.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.education.model.CsvBean;
import ru.otus.education.model.CsvTransfer;
import ru.otus.education.model.Quiz;

import java.io.*;
import java.util.List;

@RequiredArgsConstructor
public class CsvService {
    private final InputStream csvInputStream;

    public List<CsvBean> readDataFromCsv() throws IOException {
        CsvTransfer csvTransfer = new CsvTransfer();
        try (Reader reader = new BufferedReader(new InputStreamReader(csvInputStream))) {
            CsvToBean<CsvBean> csvBeans = new CsvToBeanBuilder<CsvBean>(reader)
                    .withType(Quiz.class)
                    .build();

            csvTransfer.setCsvList(csvBeans.parse());
        }
        return csvTransfer.getCsvList();
    }
}

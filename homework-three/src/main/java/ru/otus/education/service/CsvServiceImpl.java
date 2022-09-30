package ru.otus.education.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.education.exceptions.ResourceNotFoundException;
import ru.otus.education.model.CsvBean;
import ru.otus.education.model.CsvTransfer;
import ru.otus.education.model.Quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Locale;

@Service
@PropertySource(value = "application.yml")
public class CsvServiceImpl implements CsvService {
    @Value("${quiz.questions.csv.path}")
    private String csvResourcePath;

    @Override
    public InputStream getCsvResourceAsStream() {
        if (csvResourcePath != null) {
            return getClass().getResourceAsStream(getCSVResourceName());
        }
        throw new ResourceNotFoundException("Resource not found");

    }

    @Override
    public String getCSVResourceName() {
        Locale locale = LocaleContextHolder.getLocale();
        String localeString = locale.toString();
        String withoutExt = FilenameUtils.removeExtension(csvResourcePath);
        String extension = FilenameUtils.getExtension(csvResourcePath);
        return String.format("%s_%s.%s", withoutExt, localeString, extension);
    }

    @Override
    public CsvTransfer getCsvTransfer() {
        CsvTransfer csvTransfer = new CsvTransfer();
        try (Reader reader = new BufferedReader(new InputStreamReader(getCsvResourceAsStream()))) {
            CsvToBean<CsvBean> csvBeans = new CsvToBeanBuilder<CsvBean>(reader)
                    .withType(Quiz.class)
                    .build();

            csvTransfer.setCsvList(csvBeans.parse());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return csvTransfer;
    }
}

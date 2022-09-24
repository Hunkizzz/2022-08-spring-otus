package ru.otus.education.config;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.otus.education.model.CsvBean;
import ru.otus.education.model.CsvTransfer;
import ru.otus.education.model.Quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
@PropertySource(value = "test.yml")
public class CsvConfig {
    private final CsvTransfer csvTransfer;
    @Value("${quiz.questions.csv.path}")
    private String csvResourcePath;

    @Bean
    public InputStream getCsvResourceAsStream() throws FileNotFoundException {
        if (csvResourcePath != null) {
            return getClass().getResourceAsStream(getCSVResourceName());
        }
        throw new FileNotFoundException("Resource not found");

    }

    public String getCSVResourceName() {
        Locale locale = LocaleContextHolder.getLocale();
        String localeString = locale.toString();
        String withoutExt = FilenameUtils.removeExtension(csvResourcePath);
        String extension = FilenameUtils.getExtension(csvResourcePath);
        return String.format("%s_%s.%s", withoutExt, localeString, extension);
    }

    @Bean
    public List<CsvBean> readDataFromCsv() throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(getCsvResourceAsStream()))) {
            CsvToBean<CsvBean> csvBeans = new CsvToBeanBuilder<CsvBean>(reader)
                    .withType(Quiz.class)
                    .build();

            csvTransfer.setCsvList(csvBeans.parse());
        }
        return csvTransfer.getCsvList();
    }

}

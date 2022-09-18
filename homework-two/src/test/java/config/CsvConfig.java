package config;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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

@Configuration
@RequiredArgsConstructor
@PropertySource(value = "test.properties")
public class CsvConfig {
    private final CsvTransfer csvTransfer;
    @Value("${quiz.questions.csv.path}")
    private String csvResource;

    @Bean
    public InputStream getCsvResourceAsStream() throws FileNotFoundException {
        if (csvResource != null)
            return getClass().getResourceAsStream(csvResource);
        throw new FileNotFoundException("Resource not found");
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

package ru.otus.education.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.io.InputStream;

@RequiredArgsConstructor
@Data
public class CsvConfig {
    private final String csvResource;

    public InputStream getFilePath() throws FileNotFoundException {
        if (csvResource != null)
            return getClass().getResourceAsStream(csvResource);
        throw new FileNotFoundException("Resource not found");
    }
}

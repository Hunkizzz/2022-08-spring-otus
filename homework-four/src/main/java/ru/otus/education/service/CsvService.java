package ru.otus.education.service;

import ru.otus.education.model.CsvTransfer;

import java.io.InputStream;

public interface CsvService {
    InputStream getCsvResourceAsStream();

    String getCSVResourceName();

    CsvTransfer getCsvTransfer();
}

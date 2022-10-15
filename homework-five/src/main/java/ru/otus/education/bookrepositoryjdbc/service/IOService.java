package ru.otus.education.bookrepositoryjdbc.service;

public interface IOService {
    void write(String text);

    String read();

    Integer readInt();

    void write(int count);
}

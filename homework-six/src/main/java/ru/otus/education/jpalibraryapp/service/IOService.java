package ru.otus.education.jpalibraryapp.service;

public interface IOService {
    void write(String text);

    String read();

    Integer readInt();

    void write(long count);

}

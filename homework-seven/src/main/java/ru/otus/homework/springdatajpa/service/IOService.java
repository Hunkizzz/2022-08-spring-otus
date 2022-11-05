package ru.otus.homework.springdatajpa.service;

public interface IOService {
    void write(String text);

    String read();

    Integer readInt();

    void write(long count);

}

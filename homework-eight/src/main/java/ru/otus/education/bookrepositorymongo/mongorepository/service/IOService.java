package ru.otus.education.bookrepositorymongo.mongorepository.service;

public interface IOService {
    void write(String text);

    String read();

    Integer readInt();

    void write(long count);

}

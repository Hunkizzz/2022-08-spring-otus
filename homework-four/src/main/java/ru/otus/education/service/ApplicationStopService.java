package ru.otus.education.service;

public interface ApplicationStopService {
    boolean isApplicationRunning();

    void stopApplication();
}

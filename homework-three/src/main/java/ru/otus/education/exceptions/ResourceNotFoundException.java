package ru.otus.education.exceptions;

public class ResourceNotFoundException extends QuizProgramException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

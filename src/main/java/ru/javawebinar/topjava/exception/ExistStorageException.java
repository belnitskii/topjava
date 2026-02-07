package ru.javawebinar.topjava.exception;

public class ExistStorageException extends RuntimeException {
    private final String id;

    public ExistStorageException(String message, String id) {
        super(message);
        this.id = id;
    }

    public ExistStorageException(String id) {
        this("Resume " + id + " already exist", id);
    }
}

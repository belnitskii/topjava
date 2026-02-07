package ru.javawebinar.topjava.exception;

public class NotExistStorageException extends RuntimeException {
    private final String id;

    public NotExistStorageException(String message, String id) {
        super(message);
        this.id = id;
    }

    public NotExistStorageException(String id) {
        this("Resume " + id + " already exist", id);
    }
}

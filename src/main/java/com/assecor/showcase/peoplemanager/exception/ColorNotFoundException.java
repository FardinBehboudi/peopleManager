package com.assecor.showcase.peoplemanager.exception;

public class ColorNotFoundException extends Exception{
    public ColorNotFoundException() {
    }

    public ColorNotFoundException(String message) {
        super(message);
    }

    public ColorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColorNotFoundException(Throwable cause) {
        super(cause);
    }
}

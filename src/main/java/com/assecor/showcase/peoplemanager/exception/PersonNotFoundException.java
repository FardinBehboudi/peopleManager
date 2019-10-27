package com.assecor.showcase.peoplemanager.exception;



public class PersonNotFoundException extends Exception{


    public PersonNotFoundException(Throwable cause) {
        super(cause);
    }

    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.jannlouie.Config;

public class InputException extends Exception {
    String errorValue, message;

    public InputException(String message, String errorValue) {
            super(message);

        this.message = message;
        this.errorValue = errorValue;
    }

    public String getError() {
        return this.errorValue;
    }

    public String getMessage() {
        return this.message;
    }
}


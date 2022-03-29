package com.jannlouie.Config;

public class InputException extends Exception {
    private String errorValue;
    private String message;

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


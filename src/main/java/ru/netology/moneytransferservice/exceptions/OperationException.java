package ru.netology.moneytransferservice.exceptions;

abstract public class OperationException extends Throwable {
    private String id;
    private String message;

    public OperationException(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

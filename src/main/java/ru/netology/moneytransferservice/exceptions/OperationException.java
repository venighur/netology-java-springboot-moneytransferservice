package ru.netology.moneytransferservice.exceptions;

abstract public class OperationException extends Throwable {
    private int id;
    private String message;

    public OperationException(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

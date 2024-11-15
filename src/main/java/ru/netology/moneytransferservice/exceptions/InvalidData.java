package ru.netology.moneytransferservice.exceptions;

public class InvalidData extends OperationException {
    public InvalidData(String id, String msg) {
        super(id, msg);
    }
}

package ru.netology.moneytransferservice.exceptions;

public class InvalidData extends OperationException {
    public InvalidData(String msg) {
        super(1, msg);
    }
}

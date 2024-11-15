package ru.netology.moneytransferservice.exceptions;

public class TransferError extends OperationException {
    public TransferError(String msg) {
        super(2, msg);
    }
}

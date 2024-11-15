package ru.netology.moneytransferservice.exceptions;

public class TransferError extends OperationException {
    public TransferError(String id, String msg) {
        super(id, msg);
    }
}

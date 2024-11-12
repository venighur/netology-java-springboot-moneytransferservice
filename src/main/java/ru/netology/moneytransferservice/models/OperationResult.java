package ru.netology.moneytransferservice.models;

public class OperationResult {
    private String operationId;

    private OperationResult() {}

    public OperationResult(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}

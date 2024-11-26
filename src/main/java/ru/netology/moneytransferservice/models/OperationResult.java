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

    @Override
    public int hashCode() {
        return operationId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        OperationResult o = (OperationResult) obj;
        return operationId.equals(o.getOperationId());
    }
}

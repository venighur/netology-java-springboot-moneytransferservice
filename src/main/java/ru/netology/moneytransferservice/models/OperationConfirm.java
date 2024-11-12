package ru.netology.moneytransferservice.models;

public class OperationConfirm {
    public String operationId;
    public String code;

    public OperationConfirm() {}

    public OperationConfirm(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

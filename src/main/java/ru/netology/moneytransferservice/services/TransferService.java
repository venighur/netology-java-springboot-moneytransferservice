package ru.netology.moneytransferservice.services;

import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.exceptions.InvalidData;
import ru.netology.moneytransferservice.exceptions.TransferError;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;
import ru.netology.moneytransferservice.utils.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class TransferService {
    private final String INVALID_DATA_MESSAGE = "Переданы неверные данные";
    private final String TRANSFER_ERROR_MESSAGE = "При выполнении перевода произошла ошибка";
    private final String INVALID_CODE_MESSAGE = "Неверный код подтверждения";

    private final Logger logger;

    private final String operationId;

    public TransferService(String operationId) throws FileNotFoundException {
        logger = new Logger();
        this.operationId = operationId;
    }

    public TransferService() throws FileNotFoundException {
        this(UUID.randomUUID().toString());
    }

    public OperationResult completeTransfer(Operation operation) throws InvalidData, TransferError, IOException {
        logger.writeLog(new Date() + " [" + operationId + "] INFO: Запрос перевода с карты №" +
                operation.getCardFromNumber() + " на карту №" + operation.getCardToNumber() +
                ", сумма перевода " + operation.getAmount().getValue() / 100 + " " + operation.getAmount().getCurrency() +
                ", комиссия " + operation.getAmount().getValue() / 10000 + " " + operation.getAmount().getCurrency() + "\n");

        if (!isValidData(operation)) {
            logger.writeLog(new Date() + " [" + operationId + "] ERROR: " + INVALID_DATA_MESSAGE + "\n");
            throw new InvalidData(operationId, INVALID_DATA_MESSAGE);
        }

        if (!canComplete(operation)) {
            logger.writeLog(new Date() + " [" + operationId + "] ERROR: " + TRANSFER_ERROR_MESSAGE + "\n");
            throw new TransferError(operationId, TRANSFER_ERROR_MESSAGE);
        }

        return new OperationResult(operationId);
    }

    public OperationResult confirmTransfer(OperationConfirm confirm) throws TransferError, IOException {
        if (!isValidCode(confirm.getCode())) {
            logger.writeLog(new Date() + " [" + confirm.getOperationId() + "] ERROR: " + INVALID_CODE_MESSAGE + "\n");
            throw new TransferError(confirm.getOperationId(), INVALID_CODE_MESSAGE);
        }

        logger.writeLog(new Date() + " [" + confirm.getOperationId() + "] INFO: Перевод выполнен" + "\n");
        return new OperationResult(confirm.operationId);
    }

    private boolean isValidData(Operation operation) {
        // эмуляция сверки карт из запроса с данными, полученными из сторонней системы
        String cardFromNumber = "1234123412341234";
        String cardFromValidTill = "12/28";
        String cardFromCVV = "999";
        String cardToNumber = "4321432143214321";

        return operation.getCardFromNumber().equals(cardFromNumber) &&
                operation.getCardFromValidTill().equals(cardFromValidTill) &&
                operation.getCardFromCVV().equals(cardFromCVV) &&
                operation.getCardToNumber().equals(cardToNumber);
    }

    private boolean canComplete(Operation operation) {
        // эмуляция проверки возможности выполнения перевода
        String currency = "RUR";
        int value = 100_000;

        return operation.getAmount().getCurrency().equals(currency) &&
                operation.getAmount().getValue() <= value * 100;
    }

    private boolean isValidCode(String code) {
        // эмуляция проверки кода подтверждения из СМС
        String confirmCode = "0000";

        return code.equals(confirmCode);
    }
}

package ru.netology.moneytransferservice.services;

import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.exceptions.InvalidData;
import ru.netology.moneytransferservice.exceptions.TransferError;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;

import java.util.UUID;

@Service
public class TransferService {
    public OperationResult completeTransfer(Operation operation) throws InvalidData, TransferError {
        if (!isValidData(operation)) {
            throw new InvalidData("Переданы неверные данные");
        }

        if (!canComplete(operation)) {
            throw new TransferError("При выполнении перевода произошла ошибка");
        }

        return new OperationResult(UUID.randomUUID().toString());
    }

    public OperationResult confirmTransfer(OperationConfirm confirm) throws TransferError {
        if (!isValidCode(confirm.getCode())) {
            throw new TransferError("Неверный код подтверждения");
        }
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
        String currency = "RUB";
        int value = 100_000;

        return operation.getAmount().getCurrency().equals(currency) &&
                operation.getAmount().getValue() <= value;
    }

    private boolean isValidCode(String code) {
        // эмуляция проверки кода подтверждения из СМС
        String confirmCode = "0000";

        return code.equals(confirmCode);
    }
}

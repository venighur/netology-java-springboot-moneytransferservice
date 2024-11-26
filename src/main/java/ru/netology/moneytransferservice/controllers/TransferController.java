package ru.netology.moneytransferservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.exceptions.InvalidData;
import ru.netology.moneytransferservice.exceptions.TransferError;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;
import ru.netology.moneytransferservice.services.TransferService;

import java.io.IOException;

@CrossOrigin
@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public OperationResult complete(@RequestBody Operation operation) throws InvalidData, TransferError, IOException {
        return transferService.completeTransfer(operation);
    }

    @PostMapping("/confirmOperation")
    public OperationResult confirm(@RequestBody OperationConfirm confirm) throws TransferError, IOException {
        return transferService.confirmTransfer(confirm);
    }
}

package ru.netology.moneytransferservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;
import ru.netology.moneytransferservice.services.TransferService;

@CrossOrigin
@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public OperationResult completeTransfer(Operation operation) {
        return transferService.completeTransfer(operation);
    }

    @PostMapping("/confirmOperation")
    public OperationResult confirmTransfer(OperationConfirm confirm) {
        return transferService.confirmTransfer(confirm);
    }
}

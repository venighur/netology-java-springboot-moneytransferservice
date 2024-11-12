package ru.netology.moneytransferservice.services;

import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;

@Service
public class TransferService {
    public OperationResult completeTransfer(Operation operation) {
        return new OperationResult("hfgr-sdf2-dsf1");
    }

    public OperationResult confirmTransfer(OperationConfirm confirm) {
        return new OperationResult("hfgr-sdf2-dsf2");
    }
}

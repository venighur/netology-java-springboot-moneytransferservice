package ru.netology.moneytransferservice.controllers;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.moneytransferservice.exceptions.InvalidData;
import ru.netology.moneytransferservice.exceptions.TransferError;
import ru.netology.moneytransferservice.models.Amount;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;
import ru.netology.moneytransferservice.services.TransferService;

import java.io.IOException;

public class TransferControllerTests {
    @Test
    public void testComplete() throws InvalidData, TransferError, IOException {
        // arrange
        Operation operation = new Operation(
                "1234123412341234",
                "12/28",
                "999",
                "4321432143214321",
                new Amount(100_000,"RUR")
        );
        OperationResult expected = new OperationResult("123");

        TransferService service = Mockito.mock(TransferService.class);
        Mockito.when(service.completeTransfer(operation)).thenReturn(new OperationResult("123"));

        TransferController controller = new TransferController(service);

        // act
        OperationResult result = controller.complete(operation);

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testConfirm() throws TransferError, IOException {
        // arrange
        OperationConfirm confirm = new OperationConfirm("123", "0000");
        OperationResult expected = new OperationResult("123");

        TransferService service = Mockito.mock(TransferService.class);
        Mockito.when(service.confirmTransfer(confirm)).thenReturn(new OperationResult("123"));

        TransferController controller = new TransferController(service);

        // act
        OperationResult result = controller.confirm(confirm);

        // assert
        Assertions.assertEquals(expected, result);
    }
}

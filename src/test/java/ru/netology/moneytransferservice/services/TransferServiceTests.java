package ru.netology.moneytransferservice.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import ru.netology.moneytransferservice.exceptions.InvalidData;
import ru.netology.moneytransferservice.exceptions.TransferError;
import ru.netology.moneytransferservice.models.Amount;
import ru.netology.moneytransferservice.models.Operation;
import ru.netology.moneytransferservice.models.OperationConfirm;
import ru.netology.moneytransferservice.models.OperationResult;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TransferServiceTests {
    TransferService service = new TransferService("123");

    public TransferServiceTests() throws FileNotFoundException {
    }

    @Test
    public void testTransferComplete() throws InvalidData, TransferError, IOException {
        // arrange
        Operation operation = new Operation(
                "1234123412341234",
                "12/28",
                "999",
                "4321432143214321",
                new Amount(100_000,"RUR")
        );
        OperationResult expected = new OperationResult("123");

        //act
        OperationResult result = service.completeTransfer(operation);

        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testConfirmTransfer() throws TransferError, IOException {
        // arrange
        OperationConfirm confirm = new OperationConfirm("123", "0000");
        OperationResult expected = new OperationResult("123");

        // act
        OperationResult result = service.confirmTransfer(confirm);

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCompleteInvalidData() {
        // arrange
        Operation operation = new Operation(
                "1234123412341234",
                "12/28",
                "999",
                "4321432143214322",
                new Amount(100_000,"RUR")
        );
        Class<InvalidData> expected = InvalidData.class;

        // act
        Executable executable = () -> service.completeTransfer(operation);

        // assert
        Assertions.assertThrows(expected, executable);
    }

    @Test
    public void testCompleteTransferError() {
        // arrange
        Operation operation = new Operation(
                "1234123412341234",
                "12/28",
                "999",
                "4321432143214321",
                new Amount(100_000,"USD")
        );
        Class<TransferError> expected = TransferError.class;

        // act
        Executable executable = () -> service.completeTransfer(operation);

        // assert
        Assertions.assertThrows(expected, executable);
    }

    @Test
    public void testConfirmTransferError() {
        // arrange
        OperationConfirm confirm = new OperationConfirm("123", "0001");
        Class<TransferError> expected = TransferError.class;

        // act
        Executable executable = () -> service.confirmTransfer(confirm);

        // assert
        Assertions.assertThrows(expected, executable);
    }
}

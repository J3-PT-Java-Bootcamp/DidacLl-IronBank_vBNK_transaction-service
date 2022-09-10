package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import org.springframework.stereotype.Service;

public interface TransactionService {


    TransactionResult receiveBlindTransfer(TransactionRequest request);

    TransactionResult receivePaymentOrder(TransactionRequest request);

    void updatePendingTransactionTP(ConfirmationResult result);

    TransactionResult executeTransfer(TransactionRequest request);

    TransactionResult executePaymentOrder(TransactionRequest request);

    TransactionResult executeInterests(TransactionRequest request);

    TransactionResult executeBankCharge(TransactionRequest request);
}

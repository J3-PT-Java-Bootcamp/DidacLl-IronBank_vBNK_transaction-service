package com.ironhack.vbnk_transactionservice.controllers;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;

import java.security.Principal;

public interface ThirdPartyController {
    TransactionResult receiveBlindTransfer(TransactionRequest request);
    TransactionResult receivePaymentOrder(TransactionRequest request);
//    TransactionResult receivePayment(String transactionRef);
    void updatePendingTransaction(ConfirmationResult result);
}

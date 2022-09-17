package com.ironhack.vbnk_transactionservice.data.controllers;

import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.http.request.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;


public interface ThirdPartyController {
    TransactionResult receiveBlindTransfer(TransactionRequest request);
    TransactionResult receivePaymentOrder(TransactionRequest request);
//    TransactionResult receivePayment(String transactionRef);
    void updatePendingTransaction(ConfirmationResult result);
}

package com.ironhack.vbnk_transactionservice.controllers;

import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;

public interface ThirdPartyController {
    TransactionResult receiveBlindTransfer(TransactionRequest request);
    TransactionResult receivePaymentOrder(TransactionRequest request);
    void updatePendingTransaction(boolean isConfirmed,String transactionId);



}

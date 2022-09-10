package com.ironhack.vbnk_transactionservice.controllers;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;

import java.net.http.HttpResponse;
import java.security.Principal;

public interface MainController {
    HttpResponse<TransactionResult> transferTo( TransactionRequest request);

    HttpResponse<TransactionResult> orderPaymentTo(TransactionRequest request);
    void updatePendingTransaction(ConfirmationResult result);

}

package com.ironhack.vbnk_transactionservice.controllers;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;

import java.net.http.HttpResponse;
import java.security.Principal;

public interface AuthController {

    HttpResponse<TransactionResult> transfer(TransactionRequest request);

    HttpResponse<TransactionResult> orderPayment(TransactionRequest request);
    void updatePendingTransaction(ConfirmationResult result);
}

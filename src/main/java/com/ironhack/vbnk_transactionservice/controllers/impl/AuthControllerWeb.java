package com.ironhack.vbnk_transactionservice.controllers.impl;

import com.ironhack.vbnk_transactionservice.controllers.AuthController;
import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("v1/trans/auth")
public class AuthControllerWeb implements AuthController {

    @Autowired
    TransactionService service;
    @Override
    public HttpResponse<TransactionResult> transfer(TransactionRequest request) {
        return service.initiateTransferRequest(request);
    }

    @Override
    public HttpResponse<TransactionResult> orderPayment(TransactionRequest request) {
        return service.initiatePaymentRequest(request);
    }

    @Override
    public void updatePendingTransaction(ConfirmationResult result) {
        updatePendingTransaction(result);
    }
}

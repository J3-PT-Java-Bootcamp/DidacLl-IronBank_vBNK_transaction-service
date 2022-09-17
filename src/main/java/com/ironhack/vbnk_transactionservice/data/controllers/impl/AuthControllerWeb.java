package com.ironhack.vbnk_transactionservice.data.controllers.impl;

import com.ironhack.vbnk_transactionservice.data.controllers.AuthController;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.http.request.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("v1/trans/auth")
public class AuthControllerWeb implements AuthController {

    @Autowired
    TransactionService service;
    @Override
    public ResponseEntity<TransferResponse> transfer( Authentication auth, TransactionRequest request) throws ServiceUnavailableException {
//        return service.initiateTransferRequest(auth,request);
        return null; //TODO: 17/09/2022
    }

    @Override
    public HttpResponse<TransactionResult> orderPayment(TransactionRequest request) {
        return service.initiatePaymentRequest(request);
    }

    @Override
    public void updatePendingTransaction(ConfirmationResult result) {
        service.updatePendingTransaction(result);
    }
}

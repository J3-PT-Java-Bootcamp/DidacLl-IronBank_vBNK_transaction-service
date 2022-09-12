package com.ironhack.vbnk_transactionservice.controllers.impl;

import com.ironhack.vbnk_transactionservice.controllers.MainController;
import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
@RestController
@RequestMapping("v1/trans/main")
public class MainControllerWeb implements MainController {

    @Autowired
    TransactionService service;


    @Override
    @PostMapping( "/trf")
    public HttpResponse<TransactionResult> transferTo( @RequestBody TransactionRequest request) {
        return service.initiateTransferRequest(request);
    }

    @Override
    @PostMapping("/pmt")
    public HttpResponse<TransactionResult> orderPaymentTo(@RequestBody TransactionRequest request) {
        return service.initiatePaymentRequest(request);
    }

    @Override
    @PostMapping("/cnf")
    public void updatePendingTransaction(ConfirmationResult result) {

    }
}

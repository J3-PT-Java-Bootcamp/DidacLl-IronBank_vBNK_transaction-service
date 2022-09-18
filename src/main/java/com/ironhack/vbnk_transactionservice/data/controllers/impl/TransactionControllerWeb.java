package com.ironhack.vbnk_transactionservice.data.controllers.impl;

import com.ironhack.vbnk_transactionservice.data.controllers.TransactionController;
import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;
@RestController
@RequestMapping("v1/trans/main")
public class TransactionControllerWeb implements TransactionController {

    @Autowired
    TransactionService service;


    @Override
    @PostMapping( "/trf")
    public ResponseEntity<TransferResponse> transferTo( Authentication auth,@RequestBody TransferRequest request) throws ServiceUnavailableException {
        return service.initiateTransferRequest(request,auth );
    }

    @Override
    @PostMapping("/pmt")
    public HttpResponse<TransferResponse> orderPaymentTo(Authentication auth,@RequestBody TransferRequest request) {
        return service.initiatePaymentRequest(request);
    }

    @Override
    @PostMapping("/cnf")
    public void updatePendingTransaction(Authentication auth,ConfirmationResult result) {

    }
}

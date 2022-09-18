package com.ironhack.vbnk_transactionservice.data.controllers;

import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;

public interface TransactionController {
    ResponseEntity<TransferResponse> transferTo(Authentication auth, TransferRequest request) throws ServiceUnavailableException;

    HttpResponse<TransferResponse> orderPaymentTo(Authentication auth, TransferRequest request);
    void updatePendingTransaction(Authentication auth,ConfirmationResult result);

}

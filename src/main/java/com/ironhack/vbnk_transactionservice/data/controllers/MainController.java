package com.ironhack.vbnk_transactionservice.data.controllers;

import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.http.request.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;

public interface MainController {
    ResponseEntity<TransferResponse> transferTo(Authentication auth,TransactionRequest request) throws ServiceUnavailableException;

    HttpResponse<TransactionResult> orderPaymentTo(Authentication auth,TransactionRequest request);
    void updatePendingTransaction(Authentication auth,ConfirmationResult result);

}

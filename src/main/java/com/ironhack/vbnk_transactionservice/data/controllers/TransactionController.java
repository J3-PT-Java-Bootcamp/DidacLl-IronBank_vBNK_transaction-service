package com.ironhack.vbnk_transactionservice.data.controllers;

import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import com.ironhack.vbnk_transactionservice.data.http.views.StatementView;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;
import java.util.List;

public interface TransactionController {
    ResponseEntity<TransferResponse> transferTo(Authentication auth, TransferRequest request) throws ServiceUnavailableException;

    HttpResponse<TransferResponse> orderPaymentTo(Authentication auth, TransferRequest request);
    void updatePendingTransaction(Authentication auth,ConfirmationResult result);

    @PostMapping("/main/statements/{pag}")
    List<StatementView> getStatements(Authentication auth, @PathVariable(name = "pag") int pag, @RequestBody String account);
}

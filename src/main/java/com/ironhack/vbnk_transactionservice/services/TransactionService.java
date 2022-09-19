package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;

public interface TransactionService {


    ResponseEntity<TransferResponse> receiveBlindTransfer(Authentication authentication,TransferRequest request) throws ServiceUnavailableException;


    HttpResponse<TransactionDTO> getTransaction(String id);

//    TransactionDTO createTransaction(DataTransferResponse request);

    HttpResponse<TransactionDTO> updatePendingTransaction(ConfirmationResult result);

    void delete(String id);

    ResponseEntity<TransferResponse> initiateTransferRequest(Authentication authentication,TransferRequest request) throws ServiceUnavailableException;


    HttpResponse<TransferResponse> initiatePaymentRequest(TransferRequest request);
}

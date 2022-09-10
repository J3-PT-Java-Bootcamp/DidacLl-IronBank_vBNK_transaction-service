package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.time.Instant;
import java.util.List;

public interface TransactionService {


    TransactionResult receiveBlindTransfer(TransactionRequest request);

    TransactionResult receivePaymentOrder(TransactionRequest request);

    void updatePendingTransactionTP(ConfirmationResult result);

    TransactionResult executeTransfer(TransactionRequest request);

    TransactionResult executePaymentOrder(TransactionRequest request);

    TransactionResult executeInterests(TransactionRequest request);

    TransactionResult executeBankCharge(TransactionRequest request);

    HttpResponse<TransactionDTO> getTransaction(String id);

    HttpResponse<TransactionDTO> updatePendingTransaction(ConfirmationResult result);

    void delete(String id);

    HttpResponse<List<TransactionDTO>> getTransactionsByDate(Instant date);

    HttpResponse<List<TransactionDTO>> getTransactionsByDate(String userId, Instant date);

    HttpResponse<TransactionResult> initiatePaymentRequest(Principal sender, TransactionRequest request);

    HttpResponse<TransactionResult> initiateTransferRequest(Principal sender, TransactionRequest request);

    HttpResponse<TransactionResult> initiateTransferRequest(TransactionRequest request);

    HttpResponse<TransactionResult> initiatePaymentRequest(TransactionRequest request);
}

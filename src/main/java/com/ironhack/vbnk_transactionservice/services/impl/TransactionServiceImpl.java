package com.ironhack.vbnk_transactionservice.services.impl;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public TransactionResult receiveBlindTransfer(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult receivePaymentOrder(TransactionRequest request) {
        return null;
    }

    @Override
    public void updatePendingTransactionTP(ConfirmationResult result) {

    }

    @Override
    public TransactionResult executeTransfer(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executePaymentOrder(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executeInterests(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executeBankCharge(TransactionRequest request) {
        return null;
    }

    @Override
    public HttpResponse<TransactionDTO> getTransaction(String id) {
        return null;
    }

    @Override
    public HttpResponse<TransactionDTO> updatePendingTransaction(ConfirmationResult result) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public HttpResponse<List<TransactionDTO>> getTransactionsByDate(Instant date) {
        return null;
    }

    @Override
    public HttpResponse<List<TransactionDTO>> getTransactionsByDate(String userId, Instant date) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResult> initiatePaymentRequest(Principal sender, TransactionRequest request) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResult> initiateTransferRequest(Principal sender, TransactionRequest request) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResult> initiateTransferRequest(TransactionRequest request) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResult> initiatePaymentRequest(TransactionRequest request) {
        return null;
    }
}

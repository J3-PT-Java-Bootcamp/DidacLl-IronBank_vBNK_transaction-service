package com.ironhack.vbnk_transactionservice.services.impl;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.stereotype.Service;

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
}

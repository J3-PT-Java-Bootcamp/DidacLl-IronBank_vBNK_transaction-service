package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_transactionservice.data.http.request.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;
import org.springframework.stereotype.Service;

@Service
public interface ValidationService {
    TransactionResult validateTransfer(TransactionRequest request);
}

package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import org.springframework.stereotype.Service;

@Service
public interface ValidationService {
    TransactionResult validateTransfer(TransactionRequest request);
}

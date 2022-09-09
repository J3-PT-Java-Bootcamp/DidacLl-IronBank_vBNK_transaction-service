package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_transactionservice.data.TransactionType;
import org.springframework.stereotype.Service;

@Service
public interface CounterService {
    String generateID(TransactionType type);
}

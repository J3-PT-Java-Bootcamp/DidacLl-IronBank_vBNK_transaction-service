package com.ironhack.vbnk_transactionservice.data.controllers;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

public interface TransactionController {
    HttpResponse<TransactionDTO> getTransaction(String id);
    HttpResponse<TransactionDTO> updateTransaction(ConfirmationResult result);
    void deleteTransaction(String id);
    HttpResponse<List<TransactionDTO>> getAllTransByDate(Instant date);


    HttpResponse<List<TransactionDTO>> getAllTransByDateAndUser(String userId, Instant date);
}

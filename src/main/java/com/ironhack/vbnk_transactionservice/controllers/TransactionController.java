package com.ironhack.vbnk_transactionservice.controllers;

import com.ironhack.vbnk_transactionservice.data.TransactionStatus;
import com.ironhack.vbnk_transactionservice.data.dao.VBTransaction;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

public interface TransactionController {
    HttpResponse<TransactionDTO> getTransaction(String id);
    HttpResponse<TransactionDTO> updateTransaction(boolean isOk);
    void deleteTransaction(String id);
    HttpResponse<List<TransactionDTO>> getAllTransByDate(Instant date);


}

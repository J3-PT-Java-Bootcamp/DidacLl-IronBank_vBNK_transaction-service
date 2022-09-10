package com.ironhack.vbnk_transactionservice.controllers.impl;

import com.ironhack.vbnk_transactionservice.controllers.TransactionController;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
@RestController
@RequestMapping("v1/trans/dev")
public class TransactionControllerWeb implements TransactionController {
    @Override
    public HttpResponse<TransactionDTO> getTransaction(String id) {
        return null;
    }

    @Override
    public HttpResponse<TransactionDTO> updateTransaction(boolean isOk) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {

    }

    @Override
    public HttpResponse<List<TransactionDTO>> getAllTransByDate(Instant date) {
        return null;
    }
}

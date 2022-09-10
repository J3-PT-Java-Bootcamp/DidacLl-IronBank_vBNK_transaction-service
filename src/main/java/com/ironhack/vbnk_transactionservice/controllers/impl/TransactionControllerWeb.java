package com.ironhack.vbnk_transactionservice.controllers.impl;

import com.ironhack.vbnk_transactionservice.controllers.TransactionController;
import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
@RestController
@RequestMapping("v1/trans/dev")
public class TransactionControllerWeb implements TransactionController {
    @Autowired
    TransactionService service;
    @Override
    @GetMapping()
    public HttpResponse<TransactionDTO> getTransaction(@RequestParam String id) {
        return service.getTransaction(id);
    }

    @Override
    @PostMapping("/answer")
    public HttpResponse<TransactionDTO> updateTransaction(@RequestBody ConfirmationResult result) {
        return service.updatePendingTransaction(result);
    }

    @Override
    @DeleteMapping("/delete")
    public void deleteTransaction(String id) {
        service.delete(id);

    }

    @Override
    @GetMapping("/all")
    public HttpResponse<List<TransactionDTO>> getAllTransByDate(@RequestParam Instant date) {
        return service.getTransactionsByDate(date);
    }
    @Override
    @GetMapping("/all/user")
    public HttpResponse<List<TransactionDTO>> getAllTransByDateAndUser(@RequestParam String id,@RequestParam Instant date) {
        return service.getTransactionsByDate(id,date);
    }
}

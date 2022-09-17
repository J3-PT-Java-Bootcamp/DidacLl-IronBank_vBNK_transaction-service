package com.ironhack.vbnk_transactionservice.data.controllers.impl;

import com.ironhack.vbnk_transactionservice.data.controllers.ThirdPartyController;
import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/trans/client")
public class ThirdPartyControllerWeb implements ThirdPartyController {
    @Autowired
    TransactionService service;

    @Override
    @PostMapping("/in")
    public TransactionResult receiveBlindTransfer(@RequestBody TransactionRequest request) {
        return service.receiveBlindTransfer(request);
    }

    @Override
    @PostMapping("/out")
    public TransactionResult receivePaymentOrder(@RequestBody TransactionRequest request) {
        return service.receivePaymentOrder(request);
    }

    @Override
    @PostMapping("/update")

    public void updatePendingTransaction(ConfirmationResult result) {
        service.updatePendingTransactionTP(result);
    }
}

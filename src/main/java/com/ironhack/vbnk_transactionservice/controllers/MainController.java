package com.ironhack.vbnk_transactionservice.controllers;

import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;

import java.net.http.HttpResponse;
import java.security.Principal;

public interface MainController {
    HttpResponse<TransactionResult> transferTo(Principal sender, String destAccount, String originAccount);

    HttpResponse<TransactionResult> orderPaymentTo(Principal sender, String senderAccount,String destAccount);
    void updatePendingTransaction(boolean isConfirmed, String transactionRef);

}

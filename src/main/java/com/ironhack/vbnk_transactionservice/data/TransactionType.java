package com.ironhack.vbnk_transactionservice.data;

import com.ironhack.vbnk_transactionservice.data.http.request.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import com.ironhack.vbnk_transactionservice.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ironhack.vbnk_transactionservice.data.DenyReason.WRONG_INPUT;

public enum TransactionType {

    CHARGE,
    INCOME,
    PAYMENT_ORDER,
    BANK_INCOME,
    BANK_CHARGE;
    @Autowired
    TransactionService service;
    @Autowired
    ValidationService validationService;
    public TransactionResult execute(TransactionRequest request){
        var retVal= new TransactionResult();
        retVal = validationService.validateTransfer(request);
        if (retVal.isValidated()) {
                return switch (this) {
                    case CHARGE -> service.executeTransfer(request);
                    case INCOME -> service.executeCharge(request);
                    case PAYMENT_ORDER -> service.executePaymentOrder(request);
                    case BANK_CHARGE -> service.executeBankCharge(request);
                    case BANK_INCOME -> service.executeInterests(request);
                    default -> retVal.setDenyReason(WRONG_INPUT);
                };
        }
        return retVal;
    }
}

package com.ironhack.vbnk_transactionservice.data;

import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import com.ironhack.vbnk_transactionservice.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ironhack.vbnk_transactionservice.data.DenyReason.WRONG_INPUT;

public enum TransactionType {

    TRANSFER,
    PAYMENT,
    PROD_INTEREST,
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
                    case TRANSFER -> service.executeTransfer(request);
                    case PAYMENT -> service.executePaymentOrder(request);
                    case BANK_CHARGE -> service.executeBankCharge(request);
                    case PROD_INTEREST -> service.executeInterests(request);
                    default -> retVal.setDenyReason(WRONG_INPUT);
                };
        }
        return retVal;
    }
}

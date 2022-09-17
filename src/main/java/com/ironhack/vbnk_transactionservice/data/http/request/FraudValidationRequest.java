package com.ironhack.vbnk_transactionservice.data.http.request;

import com.ironhack.vbnk_transactionservice.data.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class FraudValidationRequest {

    BigDecimal amount;
    String accountRef;
    String senderRef;
    TransactionType type;

    public static FraudValidationRequest fromTransferRequest(TransferRequest request){
        return new FraudValidationRequest().setAmount(request.getAmount());
    }
}

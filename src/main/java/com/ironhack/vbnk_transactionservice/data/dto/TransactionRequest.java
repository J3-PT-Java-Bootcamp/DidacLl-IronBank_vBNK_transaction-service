package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

import static com.ironhack.vbnk_transactionservice.data.TransactionState.PENDING;

@NoArgsConstructor
@Getter @Setter
public class TransactionRequest {
    TransactionType type;
    Instant expirationDate;
    String originAccount;//could be ID or AccountNumber(if 3rd party)
    String destAccount;
    String senderID;
    BigDecimal amount;
    Currency currency;
    TransactionDetails details;


    public static TransactionDTO copyToDTO(TransactionRequest request){
        return new TransactionDTO()
                .setType(request.getType())
                .setState(PENDING)
                .setExpirationDate(request.getExpirationDate())
                .setOriginAccount(request.getOriginAccount())
                .setDestAccount(request.getDestAccount())
                .setSenderID(request.getSenderID())
                .setAmount(request.getAmount())
                .setCurrency(request.getCurrency())
                .setDetails(request.details);
    }
}

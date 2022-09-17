package com.ironhack.vbnk_transactionservice.data.http.request;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
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
    String sourceAccountRef;//could be ID or AccountNumber(if 3rd party)
    String destinationAccountRef;
    String sourceOwnerId;
    BigDecimal amount;
    Currency currency;
    TransactionDetails details;


    public static TransactionDTO copyToDTO(TransactionRequest request){
        return new TransactionDTO()
                .setType(request.getType())
                .setState(PENDING)
                .setExpirationDate(request.getExpirationDate())
                .setOriginAccount(request.getSourceAccountRef())
                .setDestAccount(request.getDestinationAccountRef())
                .setSenderID(request.getSourceOwnerId())
                .setAmount(request.getAmount())
                .setCurrency(request.getCurrency())
                .setDetails(request.details);
    }
}

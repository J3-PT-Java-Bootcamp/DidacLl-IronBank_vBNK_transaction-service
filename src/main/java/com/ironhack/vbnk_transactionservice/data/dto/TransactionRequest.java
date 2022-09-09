package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionStatus;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dao.VBTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

import static com.ironhack.vbnk_transactionservice.data.TransactionStatus.PENDING;

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




    public static TransactionDTO copyToDTO(TransactionRequest reque){
        return new TransactionDTO()
                .setType(reque.getType())
                .setStatus(PENDING)
                .setExpirationDate(reque.getExpirationDate())
                .setOriginAccount(reque.getOriginAccount())
                .setDestAccount(reque.getDestAccount())
                .setSenderID(reque.getSenderID())
                .setAmount(reque.getAmount())
                .setCurrency(reque.getCurrency())
                .setDetails(reque.details);
    }
}

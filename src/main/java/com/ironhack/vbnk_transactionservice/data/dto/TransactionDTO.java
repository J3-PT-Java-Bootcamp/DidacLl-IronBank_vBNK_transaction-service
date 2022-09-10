package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionState;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dao.VBTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@NoArgsConstructor
@Getter @Setter
public class TransactionDTO {
    String id;
    TransactionType type;

    TransactionState state;

    Instant expirationDate;

    String originAccount;//could be ID or AccountNumber(if 3rd party)
    String destAccount;
    String senderID;
    BigDecimal amount;
    Currency currency;
    Currency balanceCurrency;
    BigDecimal balanceAmount;
    TransactionResult result;
    TransactionDetails details;




    public static TransactionDTO fromEntity(VBTransaction entity){
        return new TransactionDTO().setId(entity.getId())
                .setType(entity.getType())
                .setState(entity.getState())
                .setExpirationDate(entity.getExpirationDate())
                .setOriginAccount(entity.getOriginAccountRef())
                .setDestAccount(entity.getDestAccountRef())
                .setSenderID(entity.getSenderID())
                .setAmount(entity.getQuantity().getAmount())
                .setCurrency(entity.getQuantity().getCurrency())
                .setBalanceAmount(entity.getResBalance().getAmount())
                .setBalanceCurrency(entity.getResBalance().getCurrency())
                .setResult(entity.getResult())
                .setDetails(entity.getDetails());
    }
}

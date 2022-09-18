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
    private String id;
    private TransactionType type;
    private TransactionState state;
    private Instant expirationDate;
    private Instant creationDate;
    private String ownedAccountID;
    private String foreignAccountRef;
    private String senderID;
    private BigDecimal amount;
    private Currency currency;
    private Currency balanceCurrency;
    private BigDecimal balanceAmount;
    private TransactionDetails details;




    public static TransactionDTO fromEntity(VBTransaction entity){
        return new TransactionDTO().setId(entity.getId())
                .setType(entity.getType())
                .setCreationDate(entity.getCreationDate())
                .setState(entity.getState())
                .setExpirationDate(entity.getExpirationDate())
                .setOwnedAccountID(entity.getOwnedAccountID())
                .setForeignAccountRef(entity.getForeignAccountRef())
                .setSenderID(entity.getSenderID())
                .setAmount(entity.getQuantity().getAmount())
                .setCurrency(entity.getQuantity().getCurrency())
                .setBalanceAmount(entity.getBalance().getAmount())
                .setBalanceCurrency(entity.getBalance().getCurrency())
                .setDetails(entity.getDetails());
    }
}

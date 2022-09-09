package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import com.ironhack.vbnk_transactionservice.data.TransactionStatus;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dao.VBTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@NoArgsConstructor
@Getter @Setter
public class TransactionDTO {
    String id;
    TransactionType type;

    TransactionStatus status;

    Instant expirationDate;

    String originAccount;//could be ID or AccountNumber(if 3rd party)
    String destAccount;
    String senderID;
    BigDecimal amount;
    Currency currency;


    public static TransactionDTO fromEntity(VBTransaction entity){
        return new TransactionDTO().setId(entity.getId())
                .setType(entity.getType())
                .setStatus(entity.getStatus())
                .setExpirationDate(entity.getExpirationDate())
                .setOriginAccount(entity.getOriginAccount())
                .setDestAccount(entity.getDestAccount())
                .setSenderID(entity.getSenderID())
                .setAmount(entity.getQuantity().getAmount())
                .setCurrency(entity.getQuantity().getCurrency());
    }
}

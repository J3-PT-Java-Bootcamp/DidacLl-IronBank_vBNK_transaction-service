package com.ironhack.vbnk_transactionservice.data.dao;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionState;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.utils.Money;
import com.ironhack.vbnk_transactionservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter @Setter
@NoArgsConstructor
public class VBTransaction {
    @Id
    String id;
    @Enumerated(EnumType.STRING)
    TransactionType type;
    
    @Enumerated(EnumType.STRING)
    TransactionState state;
    @Embedded
    TransactionResult result;
    @CreationTimestamp @Column(updatable = false)
    Instant creationDate;
    @UpdateTimestamp
    Instant modification;
    Instant expirationDate;
    
    String originAccountRef;//could be ID or AccountNumber(if 3rd party)
    String destAccountRef;
    String senderID;
    @Convert(converter = MoneyConverter.class)
    Money quantity;
    @Convert(converter = MoneyConverter.class)
    Money resBalance;
    @Embedded
    TransactionDetails details;
    public static VBTransaction fromDTO(TransactionDTO dto){
        return new VBTransaction().setId(dto.getId())
                .setType(dto.getType())
                .setState(dto.getState())
                .setExpirationDate(dto.getExpirationDate())
                .setOriginAccountRef(dto.getOriginAccount())
                .setDestAccountRef(dto.getDestAccount())
                .setSenderID(dto.getSenderID())
                .setQuantity(new Money(dto.getAmount(),dto.getCurrency()))
                .setResBalance(new Money(dto.getBalanceAmount(),dto.getBalanceCurrency()))
                .setResult(dto.getResult())
                .setDetails(dto.getDetails());
    }

}

package com.ironhack.vbnk_transactionservice.data.dao;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionState;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
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
    private String id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    
    @Enumerated(EnumType.STRING)
    private TransactionState state;
    @CreationTimestamp @Column(updatable = false)
    private Instant creationDate;
    @UpdateTimestamp
    private Instant modification;
    private Instant expirationDate;

    private String ownedAccountID;
    private String foreignAccountRef;
    private String senderID;
    @Convert(converter = MoneyConverter.class)
    private Money quantity;
    @Convert(converter = MoneyConverter.class)
    private Money balance;
    @Embedded
    private TransactionDetails details;
    public static VBTransaction fromDTO(TransactionDTO dto){
        return new VBTransaction().setId(dto.getId())
                .setType(dto.getType())
                .setState(dto.getState())
                .setExpirationDate(dto.getExpirationDate())
                .setOwnedAccountID(dto.getOwnedAccountID())
                .setForeignAccountRef(dto.getForeignAccountRef())
                .setSenderID(dto.getSenderID())
                .setQuantity(new Money(dto.getAmount(),dto.getCurrency()))
                .setBalance(new Money(dto.getBalanceAmount(),dto.getBalanceCurrency()))
                .setDetails(dto.getDetails());
    }




}

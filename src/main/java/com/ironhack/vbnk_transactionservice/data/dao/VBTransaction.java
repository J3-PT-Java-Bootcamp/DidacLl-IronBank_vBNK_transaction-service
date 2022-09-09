package com.ironhack.vbnk_transactionservice.data.dao;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import com.ironhack.vbnk_transactionservice.data.TransactionStatus;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
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
    TransactionStatus status;
    
    @CreationTimestamp
    Instant creationDate;
    @UpdateTimestamp
    Instant modification;
    Instant expirationDate;
    
    String originAccount;//could be ID or AccountNumber(if 3rd party)
    String destAccount;
    String senderID;
    @Convert(converter = MoneyConverter.class)
    Money quantity;
    public static VBTransaction fromDTO(TransactionDTO entity){
        return new VBTransaction().setId(entity.getId())
                .setType(entity.getType())
                .setStatus(entity.getStatus())
                .setExpirationDate(entity.getExpirationDate())
                .setOriginAccount(entity.getOriginAccount())
                .setDestAccount(entity.getDestAccount())
                .setSenderID(entity.getSenderID())
                .setQuantity(new Money(entity.getAmount(),entity.getCurrency()));
    }

}

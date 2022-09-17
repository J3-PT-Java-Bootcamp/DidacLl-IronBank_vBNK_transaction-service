package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_transactionservice.data.TransactionDetails;
import com.ironhack.vbnk_transactionservice.data.TransactionState;
import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.dao.VBTransaction;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@NoArgsConstructor
@Getter @Setter
public class TransactionMinDTO {
//    String id;
    private TransactionType type;
    private String sourceAccount;
    private String senderID;
    private BigDecimal amount;




    public static TransactionMinDTO fromEntity(VBTransaction entity){
        return new TransactionMinDTO()
                .setType(entity.getType())
                .setSenderID(entity.getSenderID())
                .setAmount(entity.getQuantity().getAmount());
    }
}

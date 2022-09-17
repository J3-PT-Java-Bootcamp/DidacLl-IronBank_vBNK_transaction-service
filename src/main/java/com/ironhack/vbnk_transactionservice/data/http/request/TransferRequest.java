package com.ironhack.vbnk_transactionservice.data.http.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    private String sourceAccountRef, sourceOwnerId, destinationAccountRef;
    @DecimalMin("0.01")
    private BigDecimal amount;
    private Currency currency;
    private String senderId;
}

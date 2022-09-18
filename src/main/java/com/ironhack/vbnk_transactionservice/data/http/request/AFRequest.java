package com.ironhack.vbnk_transactionservice.data.http.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AFRequest {
    private BigDecimal amount;
    private String srcAccountNumber,senderId;
}

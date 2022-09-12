package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_dataservice.data.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataValidationRequest {

    boolean isIncome;
    Money value;
    String accountRef;
    String ownerRef;
}

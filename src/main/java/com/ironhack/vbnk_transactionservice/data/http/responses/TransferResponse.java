package com.ironhack.vbnk_transactionservice.data.http.responses;

import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.utils.VBError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private TransferRequest request;
    private boolean isSourceLevelOk;
    private boolean isDestLevelOk;
    private BigDecimal srcPreviousBalance, destPreviousBalance;
    private List<VBError> errors;
}

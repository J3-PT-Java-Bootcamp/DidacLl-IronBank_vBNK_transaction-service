package com.ironhack.vbnk_transactionservice.data.http.responses;

import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.utils.VBError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
//@Setter
@NoArgsConstructor
public class DataTransferResponse {

    private TransferRequest request;
    private boolean source, destination, srcAccountAvailable,dstAccountAvailable, enoughFounds;
    private BigDecimal srcBalance,dstBalance;
    private List<VBError> errors;

}

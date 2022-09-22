package com.ironhack.vbnk_transactionservice.data.http.responses;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.ironhack.vbnk_transactionservice.data.TransactionState;
import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.request.UpdateTransactionRequest;
import com.ironhack.vbnk_transactionservice.utils.VBError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class TransferResponse {
    @Setter
    private TransactionState state;
    private List<VBError> errors;

    public TransferResponse addErrors(VBError... errors){
        if(this.errors==null)this.errors=List.of(errors);
        else this.errors.addAll(List.of(errors));
        return this;
    }

}

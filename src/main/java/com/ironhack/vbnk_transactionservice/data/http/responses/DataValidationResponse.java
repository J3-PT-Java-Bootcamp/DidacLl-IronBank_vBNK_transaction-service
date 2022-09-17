package com.ironhack.vbnk_transactionservice.data.http.responses;

import com.ironhack.vbnk_transactionservice.data.http.request.FraudValidationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataValidationResponse {

    FraudValidationRequest request;
    boolean userExists;
    boolean accountAvailable;
    boolean enoughFounds;

}

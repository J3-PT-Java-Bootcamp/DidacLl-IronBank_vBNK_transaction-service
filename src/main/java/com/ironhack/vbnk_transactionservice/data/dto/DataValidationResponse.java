package com.ironhack.vbnk_transactionservice.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataValidationResponse {

    DataValidationRequest request;
    boolean userExists;
    boolean accountAvailable;
    boolean enoughFounds;

}

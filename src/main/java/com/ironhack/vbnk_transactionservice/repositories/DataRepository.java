package com.ironhack.vbnk_transactionservice.repositories;

import com.ironhack.vbnk_transactionservice.data.http.request.FraudValidationRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.DataValidationResponse;
import com.ironhack.vbnk_transactionservice.utils.Money;


public interface DataRepository {

    String ping(String ping);

//    VBUserDTO getUser(String id);
//
//    AccountDTO getAccount(String ref);
//
//    List<AccountHolderDTO> getOwners(String accountRef);

    DataValidationResponse validateAccount(FraudValidationRequest request);

    boolean applyCharge(String accountRef, Money amount);

    boolean applyIncome(String accountRef, Money amount);


}

package com.ironhack.vbnk_transactionservice.repositories;

import com.ironhack.vbnk_transactionservice.data.dto.DataValidationRequest;
import com.ironhack.vbnk_transactionservice.data.dto.DataValidationResponse;
import com.ironhack.vbnk_transactionservice.utils.Money;


public interface DataRepository {

    String ping(String ping);

//    VBUserDTO getUser(String id);
//
//    AccountDTO getAccount(String ref);
//
//    List<AccountHolderDTO> getOwners(String accountRef);

    DataValidationResponse validateAccount(DataValidationRequest request);

    boolean applyCharge(String accountRef, Money amount);

    boolean applyIncome(String accountRef, Money amount);


}

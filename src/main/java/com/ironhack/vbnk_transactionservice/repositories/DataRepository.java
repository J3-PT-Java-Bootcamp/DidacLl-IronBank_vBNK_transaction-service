package com.ironhack.vbnk_transactionservice.repositories;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dto.accounts.AccountDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.VBUserDTO;
import com.ironhack.vbnk_transactionservice.data.dto.DataValidationRequest;
import com.ironhack.vbnk_transactionservice.data.dto.DataValidationResponse;

import java.util.List;

public interface DataRepository {

    public String ping(String ping);

    VBUserDTO getUser(String id);

    AccountDTO getAccount(String ref);

    List<AccountHolderDTO> getOwners(String accountRef);

    DataValidationResponse validateAccount(DataValidationRequest request);

    boolean applyCharge(String accountRef, Money amount);

    boolean applyIncome(String accountRef, Money amount);


}

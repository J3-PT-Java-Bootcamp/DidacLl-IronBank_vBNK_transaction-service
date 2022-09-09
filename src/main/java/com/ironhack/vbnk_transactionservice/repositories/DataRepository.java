package com.ironhack.vbnk_transactionservice.repositories;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.Notification;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.VBUserDTO;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository {

    VBUserDTO getUser(String id);
    List<AccountHolderDTO> getOwners(String accountRef);

}

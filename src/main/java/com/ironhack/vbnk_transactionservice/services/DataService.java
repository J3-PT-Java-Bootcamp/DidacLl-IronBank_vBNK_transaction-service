package com.ironhack.vbnk_transactionservice.services;

import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.Notification;
import org.springframework.stereotype.Repository;

@Repository
public interface DataService {

    void fraudDetected();
    boolean discountFounds(String originAccountRef,Money value);
    boolean transferFounds(String destAccountRef, Money value);
    boolean sendNotification(String userId, Notification note);
    boolean validateAccountAndFounds(String account);


}

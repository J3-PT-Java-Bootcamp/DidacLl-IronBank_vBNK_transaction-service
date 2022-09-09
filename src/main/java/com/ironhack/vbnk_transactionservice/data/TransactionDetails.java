package com.ironhack.vbnk_transactionservice.data;

import javax.persistence.Embeddable;

@Embeddable
public class TransactionDetails {
    String senderDisplayName;
    String concept;
}

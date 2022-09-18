package com.ironhack.vbnk_transactionservice.data.http.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
@Getter
public class AFResponse {
    int validationSpamBot=-1;
    int validationSpamHuman=-1;
    int validationReiterateTrans=-1;
    int validationAmountAVG=-1;
    int validationLegalReq=-1;

    boolean allValidated;

    public boolean isAllValidated(){
        return validationSpamBot<0
                ||validationSpamHuman<0
                ||validationReiterateTrans<0
                || validationAmountAVG<0
                ||validationLegalReq<0;
    }
    public boolean isAllOK(){
        return (validationSpamBot
                +validationSpamHuman
                +validationReiterateTrans
                + validationAmountAVG
                +validationLegalReq)==0;
    }
    public int getFraudLevel(){
        int total = (validationSpamBot
                +validationSpamHuman
                +validationReiterateTrans
                + validationAmountAVG
                +validationLegalReq);
        if(total>3)total=3;
        return total;

    }
}

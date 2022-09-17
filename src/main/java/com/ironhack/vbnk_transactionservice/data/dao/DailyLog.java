package com.ironhack.vbnk_transactionservice.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
public class DailyLog {
    @Id
    String id;
    @UpdateTimestamp
    LocalDate updateDate;
    Long transferCounter;
    Long paymentCounter;
    Long interestCounter;
    Long feeCounter;

}

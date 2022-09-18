package com.ironhack.vbnk_transactionservice.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
public class DailyLog {
    @Id @GenericGenerator(name = "date_based_id_generator",
            strategy = "com.ironhack.vbnk_transactionservice.utils.LogIdGenerator")
    @GeneratedValue(generator = "date_based_id_generator")

    String id;
    @UpdateTimestamp
    LocalDate updateDate;
    Long transferCounter;
    Long paymentCounter;
    Long interestCounter;
    Long feeCounter;

}

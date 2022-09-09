package com.ironhack.vbnk_transactionservice.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter @Setter
public class CounterLog {
    @Id
    @Value("counter")
    String id;

    @UpdateTimestamp
    Instant update;

    Long transferCounter;
    Long paymentCounter;
    Long interestCounter;
    Long feeCounter;

}

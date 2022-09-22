package com.ironhack.vbnk_transactionservice.data.dto;

import com.ironhack.vbnk_transactionservice.data.dao.DailyLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class LogDTO {
    String id;

    public static LogDTO fromEntity(DailyLog entity){
        return new LogDTO().setId(entity.getId());
    }
}

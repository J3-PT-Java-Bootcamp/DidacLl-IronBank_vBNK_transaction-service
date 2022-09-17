package com.ironhack.vbnk_transactionservice.repositories;

import com.ironhack.vbnk_transactionservice.data.dao.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<DailyLog,String> {
}

package com.ironhack.vbnk_transactionservice.repositories;

import com.ironhack.vbnk_transactionservice.data.dao.CounterLog;
import com.ironhack.vbnk_transactionservice.data.dao.VBTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<CounterLog,String> {
}

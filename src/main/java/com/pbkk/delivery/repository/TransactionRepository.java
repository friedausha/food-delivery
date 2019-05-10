package com.pbkk.delivery.repository;

import com.pbkk.delivery.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByCustomer_Id(int customer_id);
    List<Transaction> findAllByDriver_Id(int driver_id);
}

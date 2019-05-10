package com.pbkk.delivery.repository;

import com.pbkk.delivery.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    List<TransactionDetail> findByTransaction_Id(Integer transaction_id);
}

package com.pbkk.delivery.controller;


import com.pbkk.delivery.model.Food;
import com.pbkk.delivery.model.Transaction;
import com.pbkk.delivery.model.TransactionDetail;
import com.pbkk.delivery.repository.FoodRepository;
import com.pbkk.delivery.repository.TransactionDetailRepository;
import com.pbkk.delivery.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class TransactionDetailController {

    private final TransactionRepository transactionRepository;
    private final FoodRepository foodRepository;
    private final TransactionDetailRepository transactionDetailRepository;

    public TransactionDetailController(TransactionRepository transactionRepository,
                                       FoodRepository foodRepository,
                                       TransactionDetailRepository transactionDetailRepository) {
        this.transactionRepository = transactionRepository;
        this.foodRepository = foodRepository;
        this.transactionDetailRepository = transactionDetailRepository;
    }

    @GetMapping("/{transactionId}")
    public List<TransactionDetail> getTransactionDetails(@PathVariable Integer transactionId) {
        return transactionDetailRepository.findByTransaction_Id(transactionId);
    }

    @PostMapping("/new")
    public ResponseEntity<TransactionDetail> create(@RequestParam Integer transaction_id,
                                                    @RequestParam Integer food_id,
                                                    @RequestParam Integer amount,
                                                    @RequestParam Integer total,
                                                    @RequestParam String status) {

        Food food = foodRepository.getOne(food_id);
        Transaction transaction = transactionRepository.getOne(transaction_id);
        TransactionDetail detail = new TransactionDetail(transaction, food, amount, total, status);
        transactionDetailRepository.save(detail);
        return ResponseEntity.ok(detail);
    }
}

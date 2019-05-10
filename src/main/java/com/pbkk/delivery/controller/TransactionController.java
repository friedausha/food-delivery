package com.pbkk.delivery.controller;

import com.pbkk.delivery.model.Transaction;
import com.pbkk.delivery.model.User;
import com.pbkk.delivery.repository.TransactionRepository;
import com.pbkk.delivery.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public List<Transaction> list(@PathVariable Integer userId) {
        User user = userRepository.getOne(userId);
        if (user.getRole() == 2) {
            return transactionRepository.findAll();
        } else if (user.getRole() == 1) {
            return transactionRepository.findAllByCustomer_Id(userId);
        } else if (user.getRole() == 3) {
            return transactionRepository.findAllByDriver_Id(userId);
        } else return null;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Transaction> create(@RequestParam Integer driver_id,
                                              @RequestParam Integer customer_id,
                                              @PathVariable Integer userId) {
        User user = userRepository.getOne(userId);
        User driver = userRepository.getOne(driver_id);
        User customer = userRepository.getOne(customer_id);
        Transaction transaction = new Transaction(customer, driver);

        if (user.getRole() == 2) {
            transactionRepository.save(transaction);
            return ResponseEntity.ok(transaction);
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Transaction> update(@RequestParam Integer driver_id,
                                              @RequestParam Integer customer_id,
                                              @PathVariable Integer userId) {
        User user = userRepository.getOne(userId);
        User driver = userRepository.getOne(driver_id);
        User customer = userRepository.getOne(customer_id);
        Transaction transaction = new Transaction(customer, driver);

        if (user.getRole() == 2 || user.getRole() == 3) {
            transactionRepository.save(transaction);
            return ResponseEntity.ok(transaction);
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
}

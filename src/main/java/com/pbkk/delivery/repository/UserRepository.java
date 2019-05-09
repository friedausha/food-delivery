package com.pbkk.delivery.repository;

import com.pbkk.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByRole(int role);

    List<User> findAll();
}

package com.pbkk.delivery.repository;

import com.pbkk.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByRole(int role);

    List<User> findAll();
}

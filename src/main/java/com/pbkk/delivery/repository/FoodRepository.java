package com.pbkk.delivery.repository;

import com.pbkk.delivery.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findAllOrOrderByPrice();
}

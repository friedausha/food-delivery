package com.pbkk.delivery.controller;

import com.pbkk.delivery.model.Food;
import com.pbkk.delivery.model.User;
import com.pbkk.delivery.repository.FoodRepository;
import com.pbkk.delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<Food> index(){
        return foodRepository.findAll();
    }

    @PutMapping("/edit")
    public @ResponseBody String editFood(@Valid @RequestBody Food food, Integer id){
        User user = userRepository.getOne(id);
        if(user.getRole() == 2){
            foodRepository.save(food);
            return "Update Success";
        }
        else{
            return "Access not granted";
        }
    }

    @PostMapping("/new/{userId}")
    public @ResponseBody String addFood(@Valid @RequestBody Food food, @PathVariable Integer userId){
        User user = userRepository.getOne(userId);
        if(user.getRole() == 2){
            foodRepository.save(food);
            return "Food added";
        }
        else {
            return "Access not granted";
        }
    }
    
    @DeleteMapping("/{id}/{userId}")
    public @ResponseBody String deleteFood(@Valid @RequestBody @PathVariable Integer id, @PathVariable Integer userId){
        User user = userRepository.getOne(userId);
        if(user.getRole() == 2){
            foodRepository.deleteById(id);
            return "Food added";
        }
        else{
            return "Access not granted";
        }
    }
}

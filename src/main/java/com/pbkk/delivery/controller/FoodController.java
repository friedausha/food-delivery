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

    @GetMapping("/{id}")
    public @ResponseBody Food findById(@PathVariable Integer id) { return foodRepository.getOne(id); }

    @PutMapping("/edit}")
    public @ResponseBody String editFood(@Valid @RequestBody Food food, @RequestParam Integer userId){
        User user = userRepository.getOne(userId);
        if(user.getRole() == 2){
            foodRepository.save(food);
            return "Update Success";
        }
        else{
            return "Access not granted";
        }
    }

    @PostMapping("/new}")
    public @ResponseBody String addFood(@Valid @RequestBody Food food, @RequestParam Integer userId){
        User user = userRepository.getOne(userId);
        if(user.getRole() == 2){
            foodRepository.save(food);
            return "Food added";
        }
        else {
            return "Access not granted";
        }
    }
    
    @DeleteMapping("/{id}")
    public @ResponseBody String deleteFood(@Valid @PathVariable Integer id, @RequestParam Integer userId){
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

package com.pbkk.delivery.controller;

import com.pbkk.delivery.model.User;
import com.pbkk.delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> index(){
        return userRepository.findAllByRole(1);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userRepository.getOne(id);
    }

    @PostMapping("/new")
    public @ResponseBody User registerUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/edit")
    public @ResponseBody User editUser(@Valid @RequestBody User user){
         return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }
}

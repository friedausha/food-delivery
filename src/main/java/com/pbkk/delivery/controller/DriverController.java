package com.pbkk.delivery.controller;

import com.pbkk.delivery.model.Driver;
import com.pbkk.delivery.repository.DriverRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/{id}")
    public @ResponseBody Driver show(@PathVariable Integer id){
        return driverRepository.getOne(id);
    }

    @PutMapping("/update")
    public @ResponseBody Driver update(@RequestBody Driver driver){
        return driverRepository.save(driver);
    }
}

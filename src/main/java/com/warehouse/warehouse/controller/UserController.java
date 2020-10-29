package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Paczka;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.service.PaczkaService;
import com.warehouse.warehouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

private final UserService userService;


@PostMapping
public User addUser(@RequestBody User user){
    return  userService.addUser(user);
}
    @GetMapping("/all")
    public List<User> getAll(){
        return userService.findAll();
    }

}

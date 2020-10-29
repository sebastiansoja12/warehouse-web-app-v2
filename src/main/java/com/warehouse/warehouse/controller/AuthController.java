package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.dto.RegisterRequest;
import com.warehouse.warehouse.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest){
     authService.signup(registerRequest);
    }
}

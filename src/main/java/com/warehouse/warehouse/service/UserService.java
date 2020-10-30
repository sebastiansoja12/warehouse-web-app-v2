package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Paczka;
import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserService {


    @Autowired
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }
}

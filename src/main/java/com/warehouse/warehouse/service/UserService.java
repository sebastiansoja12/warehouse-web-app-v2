package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.User;
import com.warehouse.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


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

    @Transactional(readOnly = true)
    public User findUserIdByUsername(String username){
        return userRepository.getUsersIdByUsername(username).orElseThrow(null);
    }
}

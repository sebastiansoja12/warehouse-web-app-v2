package com.warehouse.service;

import com.warehouse.model.User;
import com.warehouse.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserIdByUsername(String username) {
        return userRepository.getUsersIdByUsername(username);
    }
}

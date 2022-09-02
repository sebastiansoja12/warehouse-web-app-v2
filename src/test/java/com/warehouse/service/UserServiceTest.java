package com.warehouse.service;


import com.warehouse.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldReturnAllUsers() {
        // given && when
        final List<User> users = userService.findAll();
        // then
        assertThat(users).isNotNull();
    }

    @Test
    void shouldFindUserByUsername() {
        // given
        final String username = "s-soja";
        // when
        final User user = userService.findUserIdByUsername(username);
        // then
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    void shouldNotFindUserByUsername() {
        // given
        final String username = "wrong-user";
        // when
        final User user = userService.findUserIdByUsername(username);
        // then
        assertThat(user).isNull();
    }
}

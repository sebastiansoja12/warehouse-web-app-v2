package com.warehouse.controller;

import com.warehouse.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void shouldReturnAllUsers() {
        // given && when
        final List<User> users = userController.getAll();
        // then
        assertThat(users).isNotNull();
    }

    @Test
    void shouldFindUserIdByUsername() {
        // given
        final String username = "s-soja";
        // when
        final User user = userController.findUserIdByUsername(username);
        // then
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(username);
    }
}

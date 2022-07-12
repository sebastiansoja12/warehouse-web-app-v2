package com.warehouse.repository;

import com.warehouse.entity.Depot;
import com.warehouse.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepotRepository depotRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveUser() {
        // given
        final Depot depot = Depot.builder()
                .city("Test")
                .street("Test street")
                .depotCode("TS1")
                .country("Test")
                .build();

        // save in db
        depotRepository.save(depot);


        final User user = User.builder()
                .username("test")
                .password("test")
                .email("test@test.test")
                .lastName("test")
                .firstName("test")
                .depot(depot)
                .role("admin")
                .build();

        // when
        userRepository.save(user);
        final int userId = user.getId();
        // then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(userId);

    }
}

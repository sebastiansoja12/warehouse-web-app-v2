package com.warehouse.repository;

import com.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    User getUsersIdByUsername(String username);

    @Query("SELECT u.role FROM User u where u.username=?1")
    String getRoleByUsername(String username);

    List<User> getUserByUsername(String username);

    List<User> findAll();

}
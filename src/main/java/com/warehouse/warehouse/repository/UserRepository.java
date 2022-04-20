package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User getUsersIdByUsername(String username);

    @Query("SELECT u.role FROM User u where u.username=?1")
    String getRoleByUsername(String username);

    List<User> getUserByUsername(String username);

    List<User> findAll();

    @Query("SELECT d.id from Depot d join User u where u.id = :depot_id")
    Long getDepotId(Long depot_id);
}
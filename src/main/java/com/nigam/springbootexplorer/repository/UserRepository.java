package com.nigam.springbootexplorer.repository;

import com.nigam.springbootexplorer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String userName);

    Optional<User> findById(Long id);
}

package com.starbus.starbus.Repository;

import com.starbus.starbus.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByIdAndPassword(String id, String password);
}

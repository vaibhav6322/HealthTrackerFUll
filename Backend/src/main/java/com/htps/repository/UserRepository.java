package com.htps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htps.entities.User;
import com.htps.entities.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByTrainerUserId(Long trainerId);
    
    
    List<User> findByRole(UserRole role); // Get all users with a specific role

    List<User> findByTrainer(User trainer); // Get all trainees under a specific trainer
    Optional<User> findById(Long userId); 
}



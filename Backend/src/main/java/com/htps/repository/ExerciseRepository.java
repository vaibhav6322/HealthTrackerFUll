package com.htps.repository;

import java.util.List;
import com.htps.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	// Fetch exercises by user ID
    List<Exercise> findByUserId(Long userId);

    
}

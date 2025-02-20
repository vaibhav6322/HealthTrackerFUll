package com.htps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htps.entities.User;

public interface TrainerRepository extends JpaRepository<User, Long> {
//	  List<User> findByTrainerTrainerId(Long trainerId);

}

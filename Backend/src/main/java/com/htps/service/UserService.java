//package com.htps.service;
//
//import com.htps.dto.Signup;
//import com.htps.dto.*;
//
//
//public interface UserService {
//	
//	Signup userRegistration(Signup reqDTO);
//	UserDTO getUserByUsername(String username);
//}


package com.htps.service;

import java.util.List;

import com.htps.dto.Signup;
import com.htps.dto.UserDTO;
import com.htps.entities.UserRole;

public interface UserService {
    Signup userRegistration(Signup reqDTO);
    UserDTO getUserByEmail(String email);
    void assignTrainer(Long userId, Long trainerId) throws Exception;
    // Change the return type to UserDTO so we can include extra info like trainerName
    List<UserDTO> getUsersByRole(UserRole role);
}

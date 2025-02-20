//package com.htps.service;
//
//import org.modelmapper.ModelMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.htps.custom_exceptions.ApiException;
//import com.htps.dto.Signup;
//import com.htps.entities.User;
//
//import com.htps.repository.UserRepository;
//
//import com.htps.dto.UserDTO;
//import com.htps.entities.User;
//import com.htps.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@Transactional
//public class UserServiceImpl implements UserService {
//	//dep : dao layer i/f
//		@Autowired
//		private UserRepository userDao;
//		//dep
//		@Autowired
//		private ModelMapper mapper;
//		//dep 
//		@Autowired
//		private PasswordEncoder encoder;
//	@Override
//	public Signup userRegistration(Signup reqDTO) {
//		//dto --> entity
//		User user=mapper.map(reqDTO, User.class);
//		if(userDao.existsByEmail(reqDTO.getEmail()))
//			throw new ApiException("Email already exists !!!");
//		
//		user.setPassword(encoder.encode(user.getPassword()));//pwd : encrypted using SHA
//		return mapper.map(userDao.save(user), Signup.class);
//	}
//	  @Override
//	    public UserDTO getUserByUsername(String username) {
//	        User user = userDao.findByUsername(username);
//	        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
//	    }
//
//}




////////
//package com.htps.service;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.htps.custom_exceptions.ApiException;
//import com.htps.dto.Signup;
//import com.htps.dto.UserDTO;
//import com.htps.entities.User;
//import com.htps.repository.UserRepository;
//
//import java.util.Optional;
//
//@Service
//@Transactional
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userDao;
//
//    @Autowired
//    private ModelMapper mapper;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Override
//    public Signup userRegistration(Signup reqDTO) {
//        User user = mapper.map(reqDTO, User.class);
//        if (userDao.existsByEmail(reqDTO.getEmail()))
//            throw new ApiException("Email already exists !!!");
//
//        user.setPassword(encoder.encode(user.getPassword())); 
//        return mapper.map(userDao.save(user), Signup.class);
//    }
//
//    @Override
//    public UserDTO getUserByEmail(String email) {
//        Optional<User> optionalUser = userDao.findByEmail(email);
//        if (optionalUser.isEmpty()) {
//            throw new ApiException("User not found with email: " + email);
//        }
//        User user = optionalUser.get();
//        return new UserDTO(user.getUserId(), user.getFirstName() + " " + user.getLastName(), user.getEmail(), user.getRole().toString());
//    }
//}
//

package com.htps.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htps.custom_exceptions.ApiException;
import com.htps.dto.Signup;
import com.htps.dto.UserDTO;
import com.htps.entities.User;
import com.htps.entities.UserRole;
import com.htps.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Signup userRegistration(Signup reqDTO) {
        User user = mapper.map(reqDTO, User.class);
        if (userDao.existsByEmail(reqDTO.getEmail()))
            throw new ApiException("Email already exists !!!");

        user.setPassword(encoder.encode(user.getPassword()));
        return mapper.map(userDao.save(user), Signup.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> optionalUser = userDao.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new ApiException("User not found with email: " + email);
        }
        User user = optionalUser.get();
        return new UserDTO(
            user.getUserId(), 
            user.getFirstName() + " " + user.getLastName(), 
            user.getEmail(), 
            user.getRole().toString(),
            (user.getTrainer() != null ? user.getTrainer().getFullName() : "Not Assigned")
        );
    }

    @Override
    public void assignTrainer(Long userId, Long trainerId) throws Exception {
        Optional<User> optionalUser = userDao.findById(userId);
        Optional<User> optionalTrainer = userDao.findById(trainerId);

        if (optionalUser.isEmpty() || optionalTrainer.isEmpty()) {
            throw new ApiException("User or Trainer not found!");
        }

        User user = optionalUser.get();
        User trainer = optionalTrainer.get();

        if (!trainer.getRole().equals(com.htps.entities.UserRole.ROLE_TRAINER)) {
            throw new Exception("Selected trainer is not a valid trainer.");
        }

//        if (trainer.getTrainees().size() >= 5) {
//            throw new Exception("A trainer can have at most 5 trainees.");
//        }

        user.setTrainer(trainer);
        userDao.save(user);
    }

    @Override
    public List<UserDTO> getUsersByRole(UserRole role) {
        List<User> users = userDao.findByRole(role);
        return users.stream()
            .map(user -> new UserDTO(
                user.getUserId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getRole().toString(),
                (user.getTrainer() != null ? user.getTrainer().getFullName() : "Not Assigned")
            ))
            .collect(Collectors.toList());
    }
}

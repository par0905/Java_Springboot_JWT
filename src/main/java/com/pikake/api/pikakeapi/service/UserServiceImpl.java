package com.pikake.api.pikakeapi.service;

import com.pikake.api.auth.dto.AuthenticationRequest;
import com.pikake.api.auth.dto.SignupRequest;
import com.pikake.api.pikakeapi.entiy.Role;
import com.pikake.api.pikakeapi.entiy.UserEntity;
import com.pikake.api.pikakeapi.model.User;
import com.pikake.api.pikakeapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }


    public User updateUser(User user){
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setUsername(user.getUsername());
        userEntity.setEnabled(user.isEnabled());

        userRepository.save(userEntity);
        return user;
    }


    public boolean deleteUser(Long id){
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
        return true;
    }


    public User getUserByUserName(String userBame){
        User user = new User();
        UserEntity userEntity = userRepository.findByUsername(userBame);
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }
    private final PasswordEncoder passwordEncoder;
    public User login(AuthenticationRequest user) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        try{
            userEntity = userRepository.findByUsername(userEntity.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("user entity password is - " + userEntity.getPassword());
        System.out.println("user passwod is - " + CharBuffer.wrap(user.getPassword()));
        if (passwordEncoder.matches(CharBuffer.wrap(user.getPassword()), userEntity.getPassword())) {
//            return userMapper.toUserDto(user);
            User user1 = new User();
            BeanUtils.copyProperties(userEntity, user1);
            return user1;

        }
        throw new Exception("Invalid password");
    }

    public User register(SignupRequest user) throws Exception {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findByUsername(userEntity.getPassword()));

        if (optionalUser.isPresent()) {
            throw new Exception("Login already exists");
        }

//        UserEntity userEntity1 = userMapper.signUpToUser(userDto);
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(user.getPassword())));

        UserEntity savedUser = userRepository.save(userEntity);

        User user1 = new User();
        BeanUtils.copyProperties(savedUser, user1);
        return user1;
    }
}

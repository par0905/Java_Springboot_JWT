package com.pikake.api.pikakeapi.service;

import com.pikake.api.pikakeapi.entiy.Role;
import com.pikake.api.pikakeapi.entiy.UserEntity;
import com.pikake.api.pikakeapi.model.User;
import com.pikake.api.pikakeapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public User createUser(User user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public User updateUser(Long id, User user){
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(Role.valueOf(user.getRole()).toString());
        userEntity.setUsername(user.getUsername());

        userRepository.save(userEntity);
        return user;
    }

    @Override
    public boolean deleteUser(Long id){
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
        return true;
    }

    @Override
    public User getUserById(Long id){
        User user = new User();
        UserEntity userEntity = userRepository.findById(id).get();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

}

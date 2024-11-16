package com.pikake.api.pikakeapi.service;
import com.pikake.api.pikakeapi.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(User user);
    User getUserById(Long  id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
}

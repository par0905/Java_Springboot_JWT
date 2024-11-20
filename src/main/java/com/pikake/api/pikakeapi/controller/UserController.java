package com.pikake.api.pikakeapi.controller;

import com.pikake.api.auth.dto.AuthenticationRequest;
import com.pikake.api.auth.dto.SignupRequest;
import com.pikake.api.pikakeapi.auth.UserAuthenticationProvider;
import com.pikake.api.pikakeapi.model.User;
import com.pikake.api.pikakeapi.repository.UserRepository;
import com.pikake.api.pikakeapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService, UserAuthenticationProvider userAuthenticationProvider){this.userService  = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }


    @PostMapping("/users")
    public User createUser(@RequestBody User user)
    {return userService.createUser(user); }

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName){
        User user = null;
        user =  userService.getUserByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{userName}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        user =  userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id){
        Map<String,Boolean> response = new HashMap<>();
        boolean deleted = false;
        deleted = userService.deleteUser(id);
        response.put("is deleted?", deleted);
        return ResponseEntity.ok(response);

    }

        @GetMapping("/csrf")
        public CsrfToken csrf(CsrfToken csrfToken) {
            return csrfToken;
        }

    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody  AuthenticationRequest credentialsDto) throws Exception {
        User userDto = userService.login(credentialsDto);
        userDto.setPassword(userAuthenticationProvider.createToken(userDto.getUsername()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignupRequest user) throws Exception {
        User createdUser = userService.register(user);
        createdUser.setPassword(userAuthenticationProvider.createToken(user.getUsername()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getUsername())).body(createdUser);
    }


}

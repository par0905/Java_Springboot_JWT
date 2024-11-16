package com.pikake.api.pikakeapi.controller;

import com.pikake.api.pikakeapi.model.User;
import com.pikake.api.pikakeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService  = userService;}


    @PostMapping("/users")
    public User createUser(@RequestBody User user)
    {return userService.createUser(user); }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = null;
        user =  userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable  Long id, @RequestBody User user){
        user =  userService.updateUser(id, user);
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

}

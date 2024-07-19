package com.example.testDemo2.controller;


import com.example.testDemo2.modal.User;
import com.example.testDemo2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            return userService.getUserById(id)
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public User createUser(@RequestBody User user) {
            return userService.saveUser(user);
        }

        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            return userService.getUserById(id)
                    .map(user -> {
                        user.setName(userDetails.getName());
                        user.setEmail(userDetails.getEmail());
                        User updatedUser = userService.saveUser(user);
                        return ResponseEntity.ok().body(updatedUser);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }



}

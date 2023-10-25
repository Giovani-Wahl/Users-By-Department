package com.giovani.usersbydepartment.controllers;

import com.giovani.usersbydepartment.domain.user.User;
import com.giovani.usersbydepartment.domain.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        user.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable (value = "id")UUID id, @RequestBody User user){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            user.setId(userOptional.get().getId());
            user.setRegistrationDate(userOptional.get().getRegistrationDate());
            user.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable (value = "id")UUID id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            userService.delete(userOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }
}

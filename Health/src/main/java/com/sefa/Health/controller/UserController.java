package com.sefa.Health.controller;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.User;
import com.sefa.Health.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser( @RequestBody User user) {
        User user1 = userService.addUser(user);

        if (user1 != null) {
            return ResponseEntity.ok("User added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Bad Request.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);

    }
}

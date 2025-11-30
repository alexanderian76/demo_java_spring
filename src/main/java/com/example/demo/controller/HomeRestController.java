package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RestController()
@RequestMapping("/homeRest")
public class HomeRestController {

    private final UserService _userService;

    public HomeRestController(UserService userService) {
        this._userService = userService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Мир") String name) {
        IO.println("QWE");
        return name;
    }

    @GetMapping("/api/allUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return _userService.getAllUsers();
    }

    // GET пользователь по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return _userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user.toDto()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<UserDto> searchUser(@RequestParam(name = "name", required = true) String name) {
        return ResponseEntity.ok(_userService.searchUser(name).toDto());
    }

    @GetMapping("/searchAll")
    public ResponseEntity<Stream<UserDto>> searchUsers(@RequestParam(name = "name", required = true) String name) {
        return ResponseEntity.ok(_userService.searchUsers(name).stream().map(u -> u.toDto()));
    }

    // POST создание пользователя
    @GetMapping("/create/{id}")
    public ResponseEntity<UserDto> createUser(@PathVariable Long id) {
        User user = new User();
       // user.setAge(7);
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail("Testqwe@mail.qwe" + id.toString());
        user.setName("Name_test");

        try {
            User createdUser = _userService.createUser(user);
            return ResponseEntity.ok(createdUser.toDto());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
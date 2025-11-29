package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public UserService() {
        // Добавляем тестовые данные
        users.add(new User(counter.incrementAndGet(), "Иван Иванов", "ivan@example.com"));
        users.add(new User(counter.incrementAndGet(), "Петр Петров", "petr@example.com"));
        users.add(new User(counter.incrementAndGet(), "Мария Сидорова", "maria@example.com"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User createUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
        return user;
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
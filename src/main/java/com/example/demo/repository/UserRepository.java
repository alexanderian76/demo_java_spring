package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA автоматически создаст реализацию
    Optional<User> findByEmail(String email);

    List<User> findByNameContainingIgnoreCase(String name);

    List<User> findByAgeGreaterThan(Integer age);

    List<User> findByAgeBetween(Integer minAge, Integer maxAge);

    // Кастомный запрос с JPQL
    @Query(value = "SELECT u.* FROM users u WHERE u.name LIKE %:keyword% OR u.email LIKE %:keyword%", nativeQuery = true)
    List<User> searchUsers(@Param("keyword") String keyword);

    // Кастомный запрос с JPQL
    @Query(value = "SELECT * FROM users u WHERE u.name LIKE %:keyword% OR u.email LIKE %:keyword% LIMIT 1", nativeQuery = true)
    User searchUser(@Param("keyword") String keyword);


    // Кастомный запрос с нативным SQL
    @Query(value = "SELECT COUNT(*) FROM users WHERE age > :minAge", nativeQuery = true)
    Long countUsersOlderThan(@Param("minAge") Integer minAge);

    boolean existsByEmail(String email);
}
package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO User (username) values (:username)")
    void insertUser(@Param("username") String username);

    @Modifying
    @Transactional
    default void insertUser(User user) {
        save(user);
    }

    List<User> getUserById(Long id);

    @Query(value = "SELECT u FROM User u")
    List<User> getAllUsers();

    @Transactional
    void deleteUserById(Long id);

    List<User> getUsersByUsername(String username);
}

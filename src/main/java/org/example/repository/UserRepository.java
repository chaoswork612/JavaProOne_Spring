package org.example.repository;

import lombok.NonNull;
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
    @Query(value = "INSERT INTO spring.users (username) values (:username)", nativeQuery = true)
    void insertUserByUsername(@Param("username") String username);

    @org.springframework.lang.NonNull
    User getReferenceById(@NonNull Long id);

    @Query(value = "SELECT u FROM User u")
    List<User> getAllUsers();

    void deleteById(@NonNull Long id);

    List<User> getUsersByUsername(String username);
}

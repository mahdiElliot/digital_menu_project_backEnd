package com.example.project.repositories.user;

import com.example.project.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users t WHERE t.username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.key = ?1", nativeQuery = true)
    Optional<User> findByKey(String key);

}

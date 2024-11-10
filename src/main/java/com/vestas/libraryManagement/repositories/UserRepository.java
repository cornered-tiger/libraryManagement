package com.vestas.libraryManagement.repositories;


import com.vestas.libraryManagement.entities.Book;
import com.vestas.libraryManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

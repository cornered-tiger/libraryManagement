package com.vestas.libraryManagement.repositories;


import com.vestas.libraryManagement.entities.Book;
import com.vestas.libraryManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

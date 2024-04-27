package dev.subrotokumar.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.subrotokumar.authentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
}

package com.mbp.mediBook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.Role;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    List<User> findByRole(Role role);
    
    List<User> findByIsActive(boolean isActive);
    
    long countByRole(Role role);
}
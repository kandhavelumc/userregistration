package com.vyapar.userregistration.repository;

import com.vyapar.userregistration.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {


    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}

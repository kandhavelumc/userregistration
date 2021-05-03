package com.vyapar.userregistration.repository;

import com.vyapar.userregistration.entity.Role;
import com.vyapar.userregistration.entity.User;
import com.vyapar.userregistration.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role,String> {
    @Query("{'name': ?0}")
    Optional<Role> findByName(ERole name);
}

package com.vyapar.userregistration.repository;

import com.vyapar.userregistration.entity.Role;
import com.vyapar.userregistration.entity.User;
import com.vyapar.userregistration.enums.ERole;
import com.vyapar.userregistration.payload.request.SignupRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends MongoRepository<User,String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String emailid);

    Role findByName(ERole roleCustomer);
}

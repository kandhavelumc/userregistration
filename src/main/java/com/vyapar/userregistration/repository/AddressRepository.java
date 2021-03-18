package com.vyapar.userregistration.repository;

import com.vyapar.userregistration.entity.Address;
import com.vyapar.userregistration.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address,String> {
}

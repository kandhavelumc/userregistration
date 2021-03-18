package com.vyapar.userregistration.controller;

import com.netflix.discovery.converters.Auto;
import com.vyapar.userregistration.entity.Role;
import com.vyapar.userregistration.entity.User;
import com.vyapar.userregistration.enums.ERole;
import com.vyapar.userregistration.payload.request.SignupRequest;
import com.vyapar.userregistration.payload.response.ResponseMessage;
import com.vyapar.userregistration.repository.AddressRepository;
import com.vyapar.userregistration.repository.RoleRepository;
import com.vyapar.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AddressRepository addressRepository;



    @PostMapping("/createuser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmailid())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Error: Email is already in use!"));
        }

        User user=new User(signUpRequest.getUsername(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getGender(),
                signUpRequest.getDateofbirth(),
                signUpRequest.getAge(),
                signUpRequest.getEmailid(),
                signUpRequest.getPrimarymobilenumber(),
                signUpRequest.getSecondarymobilenumber()
                );

        Set<String> rolesFromRequest = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (rolesFromRequest == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            roles.add(userRole);

        } else {
            rolesFromRequest.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;

                    case "vendor":
                        Role vendorRole = roleRepository.findByName(ERole.ROLE_VENDOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(vendorRole);
                            break;
                    default:
                        Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(customerRole);


                }

            });

        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new ResponseMessage("User registered successfully!"));


    }


}

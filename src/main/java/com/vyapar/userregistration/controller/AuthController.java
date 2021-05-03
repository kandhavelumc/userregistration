package com.vyapar.userregistration.controller;

import com.netflix.discovery.converters.Auto;
import com.vyapar.userregistration.entity.Address;
import com.vyapar.userregistration.entity.Role;
import com.vyapar.userregistration.entity.User;
import com.vyapar.userregistration.enums.ERole;
import com.vyapar.userregistration.payload.request.LoginRequest;
import com.vyapar.userregistration.payload.request.SignupRequest;
import com.vyapar.userregistration.payload.response.JwtResponse;
import com.vyapar.userregistration.payload.response.ResponseMessage;
import com.vyapar.userregistration.repository.AddressRepository;
import com.vyapar.userregistration.repository.RoleRepository;
import com.vyapar.userregistration.repository.UserRepository;
import com.vyapar.userregistration.security.utils.JWTUtility;
import com.vyapar.userregistration.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtility jwtUtility;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

//        Optional<User> user=userRepository.findByUsername(loginRequest.getUsername());
//        if(user == null) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new ResponseMessage("User does not exist!"));
//        }
//        if(!user.get().getPassword().equals(loginRequest.getPassword())){
//            return ResponseEntity
//                    .badRequest()
//                    .body(new ResponseMessage("Password mismatch!"));
//        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = jwtUtility.generateToken(authentication);




        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }







    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Error: Email is already in use!"));
        }

        User user=new User(signUpRequest.getUsername(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getGender(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getAge(),
                signUpRequest.getEmail(),
                signUpRequest.getPrimaryMobileNumber(),
                signUpRequest.getSecondaryMobileNumber(),
                encoder.encode(signUpRequest.getPassword())
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
        Address address=new Address(signUpRequest.getAddressLine1(),
                signUpRequest.getAddressLine2(),
                signUpRequest.getCity(),
                signUpRequest.getState(),
                signUpRequest.getCountry(),
                signUpRequest.getPinCode());
        user.setRoles(roles);
        user.setAddress(address);

        userRepository.save(user);
        return ResponseEntity.ok(new ResponseMessage("User registered successfully!"));

    }


}

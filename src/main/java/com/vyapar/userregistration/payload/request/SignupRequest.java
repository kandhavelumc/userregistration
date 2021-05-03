package com.vyapar.userregistration.payload.request;

import com.vyapar.userregistration.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String gender;
    private Date dateOfBirth;
    private int age;
    private Set<String> roles;
    private String email;
    private Long primaryMobileNumber;
    private Long secondaryMobileNumber;
    private String addressLine1;
    private String addressLine2;
    private String  state;
    private String city;
    private String country;
    private int pinCode;


}

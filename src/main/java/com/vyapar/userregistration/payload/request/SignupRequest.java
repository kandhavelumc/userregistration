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
    private String gender;
    private Date dateofbirth;
    private int age;
    private Set<String> roles;
    private String emailid;
    private Long primarymobilenumber;
    private Long secondarymobilenumber;
    private String addressline1;
    private String addressline2;
    private String  state;
    private String city;
    private String country;
    private int pincode;

}

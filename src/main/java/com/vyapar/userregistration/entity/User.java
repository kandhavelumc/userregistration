package com.vyapar.userregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 20)
    private String firstname;
    private String lastname;
    @NotBlank
    private String gender;
    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateofbirth;
    @NotBlank
    private int age;
    @NotBlank
    @DBRef
    private Set<Role> roles = new HashSet<>();
    @NotBlank
    @Size(max = 50)
    @Email
    @Indexed( unique = true)
    private String emailid;
    @NotBlank
    private Long primarymobilenumber;
    private Long secondarymobilenumber;
    @DBRef
    @NotBlank
    private Address address;


    public User(String username, String firstname, String lastname, String gender, Date dateofbirth, int age, String emailid, Long primarymobilenumber, Long secondarymobilenumber) {

    }
}

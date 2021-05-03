package com.vyapar.userregistration.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.*;
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
    @NotEmpty(message = "Username cannot be empty")    @Size(max = 20)
    private String username;
    @NotEmpty(message = "Firstname cannot be empty")
    @Size(max = 20)
    private String firstname;
    @NotEmpty(message = "Lastname cannot be empty")
    private String lastname;
    @NotEmpty(message = "Gender cannot be empty")
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private Date dateOfBirth;
    @NotNull(message = "Age cannot be empty")
    private int age;
    @DBRef
    private Set<Role> roles = new HashSet<>();
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 50)
    @Email
    @Indexed( unique = true)
    private String email;
    @NotNull(message = "PrimaryMobileNumber cannot be empty")
    private Long primaryMobileNumber;
    @NotNull(message = "SecondaryMobileNumber cannot be empty")
    private Long secondaryMobileNumber;
    private Address address;
    private String password;

   public  User(String username,String firstname,String lastname,String gender,Date dateOfBirth,int  age,String emailId,Long primaryMobileNumber,Long secondaryMobileNumber,String password)
    {
        this.username=username;
        this.firstname=firstname;
        this.lastname=lastname;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
        this.age=age;
        this.email=emailId;
        this.primaryMobileNumber=primaryMobileNumber;
        this.secondaryMobileNumber=secondaryMobileNumber;
        this.password=password;

    }


}

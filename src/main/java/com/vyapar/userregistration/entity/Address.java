package com.vyapar.userregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @NotBlank
    private String addressline1;
    private String addressline2;
    @NotBlank
    private String  state;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private int pincode;


}

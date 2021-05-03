package com.vyapar.userregistration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class Address {
    @NotEmpty(message = "AddressLine1 cannot be empty")
    private String addressLine1;
    @NotEmpty(message = "AddressLine2 cannot be empty")
    private String addressLine2;
    @NotEmpty(message = "City cannot be empty")
    private String city;
    @NotEmpty(message = "State cannot be empty")
    private String  state;
    @NotEmpty(message = "Country cannot be empty")
    private String country;
    @NotNull(message = "PinCode cannot be empty")
    private int pinCode;

    public Address(String  addressline1, String addressline2, String city, String state,String country,int pincode)
    {
        this.addressLine1=addressline1;
        this.addressLine2=addressline2;
        this.city=city;
        this.state=state;
        this.country=country;
        this.pinCode=pincode;

    }


}

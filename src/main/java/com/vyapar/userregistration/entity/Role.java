package com.vyapar.userregistration.entity;

import com.vyapar.userregistration.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Document(collection = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    private String id;
    @NotEmpty(message = "Role cannot be empty")
    private ERole name;
}

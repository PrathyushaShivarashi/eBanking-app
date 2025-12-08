package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection ="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "First name is required")
    private String lastName;
    @Email(message = " invalid email format")
    @NotBlank(message = "Email required")
    private String email;
    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;
    private String role;
    @CreatedDate
    private Instant createdAT;
    @LastModifiedDate
    private Instant  updatedAT;

}

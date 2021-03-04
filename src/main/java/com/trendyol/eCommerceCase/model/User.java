package com.trendyol.eCommerceCase.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 6, max = 26, message = "Username must be 6-26 characters long.")
    @Pattern(regexp = "^(?=[a-zA-Z0-9._])(?!.*[_.]{2})[^_.].*[^_.]$",message = "Please choose a valid username")
    private String username;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&+=])(?=\\S+$).{8,}$",message = "Please choose more safety password.")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Must be a well-formed email address.")
    private String email;
    private String name;
    private String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}

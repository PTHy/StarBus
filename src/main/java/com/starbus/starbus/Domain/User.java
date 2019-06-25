package com.starbus.starbus.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.security.MessageDigest;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(nullable = false)
    @NotEmpty
    private String id;

    @NotEmpty
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    @NotEmpty
    @Column(nullable = false, name = "phone_number", unique = true)
    private String phoneNumber;

    public void setPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(), 0, password.getBytes().length);
            this.password = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(User.class);
            logger.warn(e.getMessage());
        }
    }
}

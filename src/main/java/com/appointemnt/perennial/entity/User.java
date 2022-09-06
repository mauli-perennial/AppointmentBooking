package com.appointemnt.perennial.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This Entity is common for both patient and doctor as some  fields are necessary for both of them
 * @author Mauli Satav
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 45)
    @NotBlank(message = "User Name is required")
    @NotNull
    private String displayName;
    @Column(length = 45, unique = true)
    @NotBlank(message = "User Name is required")
    @NotNull
    private String userName;
    @NotBlank(message = "User password is required")
    @Length(min = 8, max = 20, message = "Invalid password length")
    @NotNull
    private String password;
    @Column(length = 45)
    @NotBlank(message = "User email is required")
    @NotNull
    private String email;
    @Column(nullable = true)
    private Long mobile;
    @Column(length = 45)
    @NotBlank(message = "User region is required")
    @NotNull
    private String region;

    @Column(length = 45)
    @NotBlank(message = "User role is required")
    @NotNull
    private String role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Patient patient;

    public User(@NotBlank(message = "Display Name is required") @NotNull String displayName, @NotBlank(message = "User Name is required") @NotNull String userName, @NotBlank(message = "User email is required") @NotNull String email, @NotBlank(message = "User password is required") @Length(min = 8, max = 20, message = "Invalid password length") @NotNull String password, @Nullable long mobile, @NotNull String region, @NotNull String role) {
        this.displayName = displayName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.region = region;
        this.role = role;
    }

    public User() {

    }

    public User(String displayName, String region) {
        this.displayName = displayName;
        this.region = region;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "displayName='" + displayName + '\'' + '}';
    }

}

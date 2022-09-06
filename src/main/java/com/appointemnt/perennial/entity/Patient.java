package com.appointemnt.perennial.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * This entity is extended from user and added some new fields which are common to only patients
 * @author Mauli satav
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Patient extends User {
    @Column(name = "date_of_birth")
    @NotNull
    private LocalDate dateOfBirth;
    @Column(length = 20, name = "blood_group")
    @NotBlank(message = "blood group is required")
    @NotNull
    private String bloodGroup;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private User user;
}

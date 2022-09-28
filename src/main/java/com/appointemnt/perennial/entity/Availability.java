package com.appointemnt.perennial.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * This Entity is for checking the availability of doctor
 * @author Mauli satav <mauli.satav@perennialsys.com>
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 20)
    @NotBlank(message = "day of week is required")
    @NotNull
    private String dayOfWeek;
    private LocalTime startTime;
}

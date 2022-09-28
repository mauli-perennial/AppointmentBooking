package com.appointemnt.perennial.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object for the doctor information transfer
 * @author Mauli satav
 */
@Data
@Getter
@Setter
public class DoctorDTO {
    private long id;
    private String education;
    private Integer experience;
    private String speciality;
    private String email;
    private String mobile;
    private String displayName;
    private String region;
    private String role;
}

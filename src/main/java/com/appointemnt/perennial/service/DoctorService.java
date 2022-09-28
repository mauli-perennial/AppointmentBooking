package com.appointemnt.perennial.service;

import com.appointemnt.perennial.entity.Availability;
import com.appointemnt.perennial.entity.Doctor;
import com.appointemnt.perennial.entity.User;


import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    List<Doctor> getDoctorsBySpeciality(String searchTerm);

    List<Doctor> getDoctorsByDisplayName(String searchTerm,String role);

    List<Doctor> getDoctorsByRegion(String region,String role);

    Doctor updateDoctor(User user,Long id);

    Doctor registerDoctor(Doctor doctor);
    List<Doctor> getAllDoctorBySpecialityAndRegion(String speciality,String region);

    List<Doctor> getAllDoctorByNameAndRole(String displayName,String role);
    List<Doctor> getAllDoctorByRegionAndRole(String regin,String role);
    Availability getDoctorAvailability(long id);
    List<Doctor> findAllDoctor(String searchTerm);
}

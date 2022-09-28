package com.appointemnt.perennial.service;

import com.appointemnt.perennial.dao.AvailabilityRepository;
import com.appointemnt.perennial.dao.DoctorPaginationRepository;
import com.appointemnt.perennial.dao.DoctorRepository;
import com.appointemnt.perennial.dao.UserRepository;
import com.appointemnt.perennial.entity.Availability;
import com.appointemnt.perennial.entity.Doctor;
import com.appointemnt.perennial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This class is for business logic execution related to the doctor.
 * @author Mauli Satav.
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    /**
     * Dependency injection of the doctor  pagination repository.
     */
    @Autowired
    DoctorPaginationRepository doctorPaginationRepository;
    /**
     * dependency injection of the doctor repository.
     */
    @Autowired
    private DoctorRepository doctorRepository;
    /**
     * Dependency injection of the user repo/
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * dependency injection  of the availability repo.
     */
    @Autowired
    AvailabilityRepository availabilityRepository;
    /**
     * loggers and message bundle file.
     */
    @Autowired
    public  Logger log ;
    public static final ResourceBundle bundle = ResourceBundle.getBundle("customMessage", Locale.CANADA_FRENCH);

    /**
     * getting doctor by speciality
     * @param searchTerm input as speciality
     * @return list of doctors will be returned.
     */
    @Override
    public List<Doctor> getDoctorsBySpeciality(String searchTerm) {
        List<Doctor> doctorList = doctorRepository.findAllBySpecialityContainsIgnoreCase(searchTerm);
        log.info("In doctors service find doctors by speciality");
        String message = bundle.getString("nosuchdoctors");
        if (doctorList.isEmpty()) {
            throw new RuntimeException(message);
        }
        return doctorList;
    }

    /**
     * This will return the list of doctor by dispayname
     * @param searchTerm
     * @param role
     * @return
     */
    @Override
    public List<Doctor> getDoctorsByDisplayName(String searchTerm, String role) {
        List<Doctor> doctors = doctorRepository.findAllByUserDisplayNameContainsIgnoreCaseAndUserRole(searchTerm, role);
        log.info("In doctors service find doctors by speciality");
        String message = bundle.getString("nosuchdoctors");
        if (doctors.isEmpty()) {
            throw new RuntimeException(message);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsByRegion(String region, String role) {
        List<Doctor> doctors = doctorRepository.findAllByUserRegionAndUserRole(region, role);
        log.info("In doctors service find doctors by speciality");
        String message = bundle.getString("nosuchdoctors");
        if (doctors.isEmpty()) {
            throw new RuntimeException(message);
        }
        return doctors;
    }

    /**
     * this method is for updating the doctor information
     * @param user
     * @param id
     * @return
     */
    @Override
    public Doctor updateDoctor(User user, Long id) {
        Doctor doctor1 = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("no such resource by id "));
        User user1 = doctor1.getUser();
        if (user.getMobile() != null) {
            user1.setMobile(user.getMobile());
            log.info(" in mobile update");
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            log.info("in email update");
            user1.setEmail(user.getEmail());
        }
        if (user.getRegion() != null && !user.getRegion().isEmpty()) {
            log.info("in region update");
            user1.setRegion(user.getRegion());
        }
        return doctorRepository.save(doctor1);
    }

    /**
     * persisting the doctor in the db
     * @param doctor
     * @return
     */
    @Override
    public Doctor registerDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctorBySpecialityAndRegion(String speciality, String region) {
        return doctorPaginationRepository.findBySpecialityAndZRegion(speciality, region);
    }

    /**
     * This will return the list of doctors on the basis of the name
     * @param displayName input is doctors name
     * @param role role is doctor
     * @return
     */
    @Override
    public List<Doctor> getAllDoctorByNameAndRole(String displayName, String role) {
        return doctorPaginationRepository.findAllByDisplayNameAndRole(displayName, role);
    }

    /**
     * getting the list of doctors by pagination repository.
     * @param regin input parameter.
     * @param role input parameter.
     * @return list of doctors
     */
    @Override
    public List<Doctor> getAllDoctorByRegionAndRole(String regin, String role) {
        return doctorPaginationRepository.findAllByRegionAndRole(regin, role);
    }

    /**
     * To get the doctor availability on the basis of doctorid.
     * @param id takes doctor id as input parameter.
     * @return will return the availability object
     */
    @Override
    public Availability getDoctorAvailability(long id) {
        return availabilityRepository.findByDoctorId(id);
    }

    @Override
    public List<Doctor> findAllDoctor(String searchTerm) {
        return doctorRepository.findAll().stream().filter(f->f.hasNameLike(searchTerm)).collect(Collectors.toList());
    }

}

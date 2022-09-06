package com.appointemnt.perennial.controller;

import com.appointemnt.perennial.dto.DoctorDTO;
import com.appointemnt.perennial.dto.UserDTO;
import com.appointemnt.perennial.entity.Availability;
import com.appointemnt.perennial.entity.Doctor;
import com.appointemnt.perennial.entity.User;
import com.appointemnt.perennial.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller belong to the doctor all request related to doctor will dispatched by this controller
 * @author  Mauli Satav mauli.satav@perennialsys.com
 */
@RestController
@RequestMapping(path= "/doctor",produces = "application/xml")
public class DoctorController {
    /**
     * dependency injection of doctor service
     */
    @Autowired
    private DoctorService doctorService;

    /**
     * dependency injected of model mapper
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     * @param speciality speciality of doctors to search doctors
     * @param region  region of the doctors to search doctors by region
     * @return return the list of doctor in xml format with matching criteria
     */
    @RequestMapping(value ="/api/search-by-speciality",produces = {MediaType.APPLICATION_XML_VALUE},method = RequestMethod.GET)
    public @ResponseBody List<DoctorDTO> getDoctorsBySpeciality(@RequestParam(name = "speciality") String speciality,@RequestParam(name = "region") String region) {
        if (speciality.isBlank() || region.isBlank()) {
            throw new RuntimeException("searchTerm should not be blank");
        }
        return doctorService.getAllDoctorBySpecialityAndRegion(speciality,region).stream().map(doc -> modelMapper.map(doc, DoctorDTO.class)).collect(Collectors.toList());
    }

    /**
     *
     * @param name name of the doctor
     * @param role role of users
     * @return return the doctors by name and their role
     */
    @GetMapping("/api/search-by-name")
    public List<DoctorDTO> getDoctorsByName(@RequestParam(name= "name") String name, @RequestParam(name= "role") String role) {
        if (name.isBlank() || role.isBlank()) {
            throw new RuntimeException("searchTerm should not be blank");
        }
        return doctorService.getAllDoctorByNameAndRole(name, role).stream().map(doctor -> modelMapper.map(doctor, DoctorDTO.class)).collect(Collectors.toList());
    }

    /**
     *
     * @param region search doctor by particular region
     * @param role
     * @return return the list of doctor by region
     */
    @GetMapping("/api/search-by-region")
    public List<DoctorDTO> getDoctorsByRegion(@RequestParam String region, @RequestParam String role) {
        if (region.isBlank() || role.isBlank()) {
            throw new RuntimeException("searchTerm should not be blank");
        }
        return doctorService.getDoctorsByRegion(region, role).stream().map(doctor -> modelMapper.map(doctor, DoctorDTO.class)).collect(Collectors.toList());
    }

    /**
     *
     * @param id id of doctor to be updated
     * @param userDTO
     * @return
     */
    @PatchMapping("/api/update-doctor")
    public ResponseEntity<DoctorDTO> updateDoctorMobile(@RequestParam Long id, @RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Doctor doctor1 = doctorService.updateDoctor(user, id);
        DoctorDTO doctorDTO = modelMapper.map(doctor1, DoctorDTO.class);
        return ResponseEntity.ok(doctorDTO);
    }

    /**
     * this is for adding new doctors to the system
     * @param doctor will take input as doctor
     * @return
     */
    @PostMapping("/api/doctors")
    public ResponseEntity<DoctorDTO> registerUser(@RequestBody @Valid Doctor doctor) {
        Doctor doctor1 = doctorService.registerDoctor(doctor);
        DoctorDTO doctorDTO = modelMapper.map(doctor1, DoctorDTO.class);
        return  ResponseEntity.ok(doctorDTO);
    }

    /**
     * to check the availability of doctor by doctorid
     * @param doctorId
     * @return this will return the availability of doctor
     */
   @GetMapping("/api/availability")
    public Availability getDoctorAvailability(@RequestParam(value = "id") Long doctorId){
        if(doctorId ==null){
            throw new RuntimeException(" id should not be null");
        }
        return doctorService.getDoctorAvailability(doctorId);
   }
}


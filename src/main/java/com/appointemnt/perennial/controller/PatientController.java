package com.appointemnt.perennial.controller;

import com.appointemnt.perennial.entity.Patient;
import com.appointemnt.perennial.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller belongs to the patient all the operation belonging to patient will be here
 * @author Mauli Satav <mauli.satav@perennialsys.com>
 */
@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    /**
     * Dependency injection of the patient service
     */
    @Autowired
    private PatientService patientService;

    /**
     * This api is to registration of patient
     * @param patient
     * @return
     */
    @RequestMapping(value = "/add-patient", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody @Valid Patient patient) {
        return new ResponseEntity<>(patientService.registerPatient(patient), HttpStatus.CREATED);
    }
}

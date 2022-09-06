package com.appointemnt.perennial.service;

import com.appointemnt.perennial.dao.PatientRepository;
import com.appointemnt.perennial.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This class is for business logic of the patients related
 * @author Mauli Satav.
 */
@Service
@Transactional
public class PatientServiceImpl implements PatientService{
    /**
     * dependency injection of patient repository
     */
    @Autowired
    private PatientRepository patientRepository;

    /**
     * @param patient takes patient as input parameter
     * @return return string when we successfully persist patient.
     */
    @Override
    public String registerPatient(Patient patient) {
        patientRepository.save(patient);
        return "patient registered successfully ";
    }
}

package com.appointemnt.perennial.dao;

import com.appointemnt.perennial.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Override
    <S extends Patient> S save(S entity);
}
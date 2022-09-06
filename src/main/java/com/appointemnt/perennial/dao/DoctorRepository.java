package com.appointemnt.perennial.dao;

import com.appointemnt.perennial.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllBySpecialityContainsIgnoreCase(String searchTerm);

    List<Doctor> findAllByUserDisplayNameContainsIgnoreCaseAndUserRole(String searchTerm,String role);

    List<Doctor> findAllByUserRegionAndUserRole(String region,String role);

    Optional<Doctor> findById(Long id);

    @Override
    <S extends Doctor> S save(S entity);


}
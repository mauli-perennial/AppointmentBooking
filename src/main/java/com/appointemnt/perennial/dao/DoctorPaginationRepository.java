package com.appointemnt.perennial.dao;

import com.appointemnt.perennial.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is pagination and sorting repository for the doctors on the basis of differtn criteria
 */
@Repository
public interface DoctorPaginationRepository extends PagingAndSortingRepository<Doctor, Long> {
    /**
     * finding doctors by speciality and region
     *
     * @param speciality
     * @param region
     * @return LIST OF doctors by matching speciality and region
     */
    @Query(nativeQuery = true, value = "select * from doctor d , user u where d.speciality =?1 and u.region =?2")
    List<Doctor> findBySpecialityAndZRegion(String speciality, String region);

    /**
     * This is for finding the doctors by matching displayName and role
     *
     * @param displayName name of the doctors
     * @param role        role is doctor
     * @return will return the list of doctors by matching displayname
     */
    List<Doctor> findAllByDisplayNameAndRole(String displayName, String role);

    /**
     * Find the doctors by particular reion and role
     *
     * @param region region is address of doctor
     * @param role   role is dotor
     * @return list of doctor by matching criteria
     */
    List<Doctor> findAllByRegionAndRole(String region, String role);
}


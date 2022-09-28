package com.appointemnt.perennial.dao;

import com.appointemnt.perennial.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Availability repository to check the availability of doctor by doctor_id.
 * @author Mauli Satav <mauli.satav@perennialsys.com>
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability,Long> {
    /**
     * Query to fetch doctors availability on th basis of doctor_id
     * @param id id of doctor will be input
     * @return Availability object in the basis of doctor_id
     */
    @Query(nativeQuery = true,value = "select * from availability where doctor_id =?")
    Availability findByDoctorId(long id);
}

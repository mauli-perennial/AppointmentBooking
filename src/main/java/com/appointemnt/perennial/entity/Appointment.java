package com.appointemnt.perennial.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Appointment entity is for the booking the appointment with the particular doctor
 * @author Mauli satav <mauli.satav@perennialsys.com>
 */
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctorId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patientId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    public Integer getId() {
        return id;
    }


    public Doctor getDoctorId() {
        return doctorId;
    }


    public Patient getPatientId() {
        return patientId;
    }


    public LocalDate getDate() {
        return date;
    }


    public LocalTime getStartTime() {
        return startTime;
    }


    public LocalTime getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

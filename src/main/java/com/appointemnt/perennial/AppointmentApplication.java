package com.appointemnt.perennial;

import com.appointemnt.perennial.service.DoctorServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AppointmentApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public  Logger getLogger(){
       Logger log = LogManager.getLogger(String.valueOf(AppointmentApplication.class));
        PropertyConfigurator.configure("log4j.properties");
        return log;
    }
    public static void main(String[] args) {
        SpringApplication.run(AppointmentApplication.class, args);
    }


}

package com.example.VBS.controller;

import com.example.VBS.Model.Appointment;
import com.example.VBS.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PutMapping("bookAppointment")
    public ResponseEntity bookAppointment(@RequestParam("personId") int personId){
        try {
            Appointment appointment = appointmentService.bookAppointment(personId);
            return new ResponseEntity("Appointment booked for "+appointment.getDate(), HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

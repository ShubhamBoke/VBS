package com.example.VBS.controller;

import com.example.VBS.Dto.RequestDto.AddDoctorRequestDto;
import com.example.VBS.Dto.RequestDto.AddPersonRequestDto;
import com.example.VBS.Dto.ResponseDto.AddDoctorResponseDto;
import com.example.VBS.Dto.ResponseDto.AddPersonResponseDto;
import com.example.VBS.Model.Doctor;
import com.example.VBS.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody AddDoctorRequestDto doctor){
        try {
            System.out.println("yo");
            AddDoctorResponseDto d = doctorService.addDoctor(doctor);
            return new ResponseEntity(d, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}

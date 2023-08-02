package com.example.VBS.controller;

import com.example.VBS.Model.VaccinationCentre;
import com.example.VBS.service.VaccinationCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vaccination_centre")
public class VaccinationCentreController {

    @Autowired
    VaccinationCentreService vaccinationCentreService;

    @PostMapping("add")
    public ResponseEntity addVaccinationCentre(@RequestBody VaccinationCentre vaccinationCentre){
        try {
            VaccinationCentre vc = vaccinationCentreService.addVaccinationCentre(vaccinationCentre);
            return new ResponseEntity(vc, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Failed", HttpStatus.EXPECTATION_FAILED);
        }
    }
}

package com.example.VBS.controller;

import com.example.VBS.Dto.RequestDto.AddPersonRequestDto;
import com.example.VBS.Dto.ResponseDto.AddPersonResponseDto;
import com.example.VBS.Enum.Vaccine;
import com.example.VBS.Model.Dose;
import com.example.VBS.Model.Person;
import com.example.VBS.service.DoseService;
import com.example.VBS.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    DoseService doseService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto person){
        try {
            AddPersonResponseDto p = personService.addPerson(person);
            return new ResponseEntity(p, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Failed", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/get_dose")
    public ResponseEntity getDose(@RequestParam("personId") int personId, @RequestParam("vaccine") Vaccine vaccine){
        try {
            Dose d = doseService.getDose(personId, vaccine);
            return new ResponseEntity(d, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePerson(@RequestParam("personId") int personId){
        try {
            personService.deletePerson(personId);
            return new ResponseEntity("Person deleted Successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}

package com.example.VBS.service;

import com.example.VBS.Dto.RequestDto.AddDoctorRequestDto;
import com.example.VBS.Dto.RequestDto.AddPersonRequestDto;
import com.example.VBS.Dto.ResponseDto.AddDoctorResponseDto;
import com.example.VBS.Dto.ResponseDto.AddPersonResponseDto;
import com.example.VBS.Model.Doctor;
import com.example.VBS.Model.VaccinationCentre;
import com.example.VBS.repository.DoctorRepository;
import com.example.VBS.repository.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;

    public AddDoctorResponseDto addDoctor(AddDoctorRequestDto doctor) throws RuntimeException{

        //Converting Dto to obj
        Doctor d = new Doctor();
        d.setName(doctor.getName());
        d.setAge(doctor.getAge());
        d.setGender(doctor.getGender());
        d.setCity(doctor.getCity());
        d.setEmail(doctor.getEmail());

        //Adding doctor to VC
        List<VaccinationCentre> vaccinationCentreList = vaccinationCentreRepository.findAll();
        for(VaccinationCentre vc: vaccinationCentreList){
            if(vc.getCity() == doctor.getCity()){
                vc.getDoctorList().add(d);
                d.setVaccinationCentre(vc);
                break;
            }
        }
        d = doctorRepository.save(d);

        //Converting obj to Dto
        AddDoctorResponseDto out = new AddDoctorResponseDto();
        out.setId(d.getId());
        out.setName(d.getName());
        out.setAge(d.getAge());
        out.setGender(d.getGender());
        out.setCity(d.getCity());
        out.setEmail(d.getEmail());
        out.setVaccinationCentre(d.getVaccinationCentre());
        return out;

    }
}

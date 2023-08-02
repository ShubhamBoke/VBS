package com.example.VBS.service;

import com.example.VBS.Model.VaccinationCentre;
import com.example.VBS.repository.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCentreService {

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;

    public VaccinationCentre addVaccinationCentre(VaccinationCentre vaccinationCentre) {
        return vaccinationCentreRepository.save(vaccinationCentre);
    }
}

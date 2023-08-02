package com.example.VBS.Model;

import com.example.VBS.Enum.City;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    @Enumerated(EnumType.STRING)
    City city;

    @OneToMany(mappedBy = "vaccinationCentre", cascade = CascadeType.ALL)
    List<Doctor> doctorList = new ArrayList<>();

    @OneToMany(mappedBy = "vaccinationCentre", cascade = CascadeType.ALL)
    List<Appointment> appointmentList = new ArrayList<>();
}

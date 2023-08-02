package com.example.VBS.Model;

import com.example.VBS.Enum.City;
import com.example.VBS.Enum.Gender;
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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(unique = true, nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    City city;

    @ManyToOne
    @JoinColumn
    VaccinationCentre vaccinationCentre;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    List<Appointment> appoinmentList = new ArrayList<>();

}

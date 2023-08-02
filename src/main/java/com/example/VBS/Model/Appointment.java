package com.example.VBS.Model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Person person;

    @ManyToOne
    @JoinColumn
    Doctor doctor;

    @OneToOne
    @JoinColumn
    Dose dose;

    @ManyToOne
    @JoinColumn
    VaccinationCentre vaccinationCentre;



}

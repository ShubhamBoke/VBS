package com.example.VBS.Model;

import com.example.VBS.Enum.City;
import com.example.VBS.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

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

    boolean dose1Taken;
    boolean dose2Taken;

    @OneToOne(cascade = CascadeType.ALL)
    Appointment appointment;
}

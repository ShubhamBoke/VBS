package com.example.VBS.Model;


import com.example.VBS.Enum.Vaccine;
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
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Date date;

    @Enumerated(EnumType.STRING)
    Vaccine vaccine;

    @ManyToOne
    @JoinColumn
    Person person;
}

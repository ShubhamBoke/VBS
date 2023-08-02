package com.example.VBS.Dto.ResponseDto;

import com.example.VBS.Enum.City;
import com.example.VBS.Enum.Gender;
import com.example.VBS.Model.VaccinationCentre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDoctorResponseDto {
    int id;
    String name;
    int age;
    Gender gender;
    String email;
    City city;
    VaccinationCentre vaccinationCentre;
}

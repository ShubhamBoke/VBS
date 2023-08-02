package com.example.VBS.Dto.RequestDto;

import com.example.VBS.Enum.City;
import com.example.VBS.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDoctorRequestDto {
    String name;
    int age;
    Gender gender;
    String email;
    City city;
}

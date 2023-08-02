package com.example.VBS.Dto.ResponseDto;

import com.example.VBS.Enum.City;
import com.example.VBS.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPersonResponseDto {
    int id;
    String name;
    int age;
    Gender gender;
    City city;
    String email;
}

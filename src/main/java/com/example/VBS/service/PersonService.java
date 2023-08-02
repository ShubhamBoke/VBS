package com.example.VBS.service;

import com.example.VBS.Dto.RequestDto.AddPersonRequestDto;
import com.example.VBS.Dto.ResponseDto.AddPersonResponseDto;
import com.example.VBS.Model.Person;
import com.example.VBS.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    JavaMailSender javaMailSender;


    public AddPersonResponseDto addPerson(AddPersonRequestDto person) {

        //Converting Dto to obj
        Person in = new Person();
        in.setName(person.getName());
        in.setAge(person.getAge());
        in.setGender(person.getGender());
        in.setCity(person.getCity());
        in.setEmail(person.getEmail());

        Person p = personRepository.save(in);

        //Converting obj to Dto
        AddPersonResponseDto out = new AddPersonResponseDto();
        out.setId(p.getId());
        out.setName(p.getName());
        out.setAge(p.getAge());
        out.setGender(p.getGender());
        out.setCity(p.getCity());
        out.setEmail(p.getEmail());

        //Sending Mail
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shbgaming7@gmail.com");
        simpleMailMessage.setTo(person.getEmail());
        simpleMailMessage.setSubject("Congrats!! You have been registered successfully.\n");
        simpleMailMessage.setText("Registration is successful on Vaccination Booking System\n" +
                "Please verify your details ->\n" +
                "Name: "+ person.getName()+"\n"+
                "Age: "+ person.getAge()+"\n"+
                "Gender: "+ person.getGender()+"\n"+
                "City: "+ person.getCity());
        javaMailSender.send(simpleMailMessage);

        return out;
    }

    public void deletePerson(int personId) throws RuntimeException{
        personRepository.deleteById(personId);
    }
}

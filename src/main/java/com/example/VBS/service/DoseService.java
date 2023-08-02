package com.example.VBS.service;

import com.example.VBS.Enum.Vaccine;
import com.example.VBS.Model.Dose;
import com.example.VBS.Model.Person;
import com.example.VBS.repository.DoseRepository;
import com.example.VBS.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public Dose getDose(int personId, Vaccine vaccine) throws Exception {
        Optional<Person> response = personRepository.findById(personId);
        Dose dose = new Dose();
        Person person = null;
        if(response.isPresent()){
            person = response.get();
            if(person.getAppointment() != null){
                if(person.isDose1Taken()){
                    List<Dose> doseList = doseRepository.findAll();
                    Dose prevDose = null;
                    for(Dose d: doseList){
                        if(d.getPerson() == person){
                            prevDose = d;
                            break;
                        }
                    }
                    if(prevDose.getVaccine() != vaccine) throw new Exception("Please select same vaccine that was taken for dose 1");
                    dose.setDate(person.getAppointment().getDate());
                    dose.setPerson(person);
                    dose.setVaccine(vaccine);
                    person.setDose2Taken(true);
                    person.getAppointment().setDose(dose);
                    person.setAppointment(null);

                } else{
                    dose.setDate(person.getAppointment().getDate());
                    dose.setPerson(person);
                    dose.setVaccine(vaccine);
                    person.setDose1Taken(true);
                    person.getAppointment().setDose(dose);
                    person.setAppointment(null);

                }
            } else
                throw new Exception("Please book appointment first");
        } else
            throw new Exception("Enter valid personId");

        //Sending Mail
        String dosestr = "";
        if(person.isDose1Taken())
            dosestr = "Dose 2";
        else
            dosestr = "Dose 1";

        String text = "";
        if(person.isDose2Taken())
            text = "Congrates!! Now you are fully vaccinated with both doses!\n";
        else
            text = "Congrats!! You just completed you Dose 1\nNow you can get you Dose 2 by booking an Appointment\n";

        String details = "Vaccine taken: "+vaccine.name()+"\n" +
                "Date: "+ dose.getDate();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shbgaming7@gmail.com");
        simpleMailMessage.setTo(person.getEmail());
        simpleMailMessage.setSubject("Congrats!! You just got vaccinated.\n");
        simpleMailMessage.setText(text+details);
        javaMailSender.send(simpleMailMessage);

        return doseRepository.save(dose);
    }
}

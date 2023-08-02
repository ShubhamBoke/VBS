package com.example.VBS.service;

import com.example.VBS.Model.Appointment;
import com.example.VBS.Model.Doctor;
import com.example.VBS.Model.Person;
import com.example.VBS.Model.VaccinationCentre;
import com.example.VBS.repository.AppointmentRepository;
import com.example.VBS.repository.PersonRepository;
import com.example.VBS.repository.VaccinationCentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    VaccinationCentreRepository vaccinationCentreRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public Appointment bookAppointment(int personId) throws Exception {
        Appointment appointment = new Appointment();
        Person person = personRepository.getReferenceById(personId);
        if(person.getAppointment() != null) throw new Exception("You already have an Appointment booked for "+person.getAppointment().getDate());

        //Getting Vaccination Centre
        List<VaccinationCentre> vaccinationCentreList = vaccinationCentreRepository.findAll();
        VaccinationCentre vaccinationCentre = null;

        for(VaccinationCentre vc: vaccinationCentreList){
            if(vc.getCity() == person.getCity()){
                vaccinationCentre = vc;
                break;
            }
        }
        if(vaccinationCentre == null) throw new Exception("No Vaccination Centre Available");

        //Assigning Doctor
        List<Doctor> doctorList = vaccinationCentre.getDoctorList();
        if(doctorList.size() == 0) throw new Exception("No Doctors Available");
        Doctor doctor = doctorList.get(0);


        if(person.isDose1Taken()){
            if(person.isDose2Taken())
                throw new Exception("Both Doses Taken");
            else{
                appointment.setPerson(person);
                appointment.setDate(new Timestamp(System.currentTimeMillis()));
                appointment.setVaccinationCentre(vaccinationCentre);
                appointment.setDoctor(doctor);
            }
        } else{
            appointment.setPerson(person);
            appointment.setDate(new Timestamp(System.currentTimeMillis()));
            appointment.setVaccinationCentre(vaccinationCentre);
            appointment.setDoctor(doctor);
        }

        person.setAppointment(appointment);
        doctor.getAppoinmentList().add(appointment);
        vaccinationCentre.getAppointmentList().add(appointment);

        //Sending Mail to person
        String dose = "";
        if(person.isDose1Taken())
            dose = "Dose 2";
        else
            dose = "Dose 1";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shbgaming7@gmail.com");
        simpleMailMessage.setTo(person.getEmail());
        simpleMailMessage.setSubject("Congrats!! Appointment booked successfully.\n");
        simpleMailMessage.setText("We have booked you appointment for "+dose+"\n"+
                "Please verify your details ->\n" +
                "Name: "+ person.getName()+"\n"+
                "Age: "+ person.getAge()+"\n"+
                "Gender: "+ person.getGender()+"\n"+
                "City: "+ person.getCity()+"\n\n\n" +
                "Doctor assigned: Dr. "+ doctor.getName()+"\n" +
                "Date: "+ appointment.getDate()+"\n" +
                "Venue: "+ appointment.getVaccinationCentre().getName()+"\n\n" +
                "Please be present for getting your dose on the mentioned vaccination centre on time.");
        javaMailSender.send(simpleMailMessage);

        return appointmentRepository.save(appointment);

    }
}

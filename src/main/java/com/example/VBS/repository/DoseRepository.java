package com.example.VBS.repository;

import com.example.VBS.Model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Integer> {

}

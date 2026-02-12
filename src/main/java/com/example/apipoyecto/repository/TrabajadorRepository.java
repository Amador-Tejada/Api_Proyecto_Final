package com.example.apipoyecto.repository;

import com.example.apipoyecto.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador , Long> {
    List<Trabajador> findByPuesto(String puesto);
}

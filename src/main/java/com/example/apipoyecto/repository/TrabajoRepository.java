package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.EstadoTrabajo;
import com.example.apipoyecto.model.PrioridadTrabajo;
import com.example.apipoyecto.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {

    List<Trabajo> findByClienteId(Long clienteId);

    List<Trabajo> findByTrabajadorId(Long trabajadorId);

    List<Trabajo> findByEstado(EstadoTrabajo estado);

    List<Trabajo> findByPrioridad(PrioridadTrabajo prioridad);

    List<Trabajo> findByFechaProgramadaBetween(LocalDate inicio, LocalDate fin);
}
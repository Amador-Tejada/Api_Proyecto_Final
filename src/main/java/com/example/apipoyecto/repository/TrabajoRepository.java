package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.EstadoTrabajo;
import com.example.apipoyecto.model.PrioridadTrabajo;
import com.example.apipoyecto.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/*
* Repositorio para la entidad Trabajo, que extiende JpaRepository para proporcionar métodos CRUD y consultas personalizadas.
* Permite buscar trabajos por cliente, trabajador, estado, prioridad y rango de fechas programadas.
* */
@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {

    List<Trabajo> findByClienteId(Long clienteId);

    List<Trabajo> findByTrabajadorId(Long trabajadorId);

    List<Trabajo> findByEstado(EstadoTrabajo estado);

    List<Trabajo> findByPrioridad(PrioridadTrabajo prioridad);

    List<Trabajo> findByFechaProgramadaBetween(LocalDate inicio, LocalDate fin);
}
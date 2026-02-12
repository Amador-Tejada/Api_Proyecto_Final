package com.example.apipoyecto.repository;

import com.example.apipoyecto.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* Repositorio para la entidad Trabajador, que extiende JpaRepository para proporcionar métodos CRUD.
* Además, se define un método personalizado para buscar trabajadores por su puesto.
 */
@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador , Long> {
    List<Trabajador> findByPuesto(String puesto);
}

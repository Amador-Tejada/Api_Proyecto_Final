package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* Repositorio para la entidad Producto, que extiende JpaRepository para proporcionar métodos CRUD.
 * Además, se define un método personalizado para buscar productos por su nombre.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);
}
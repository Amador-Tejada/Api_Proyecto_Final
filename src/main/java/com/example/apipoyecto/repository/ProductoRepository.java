package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);
}
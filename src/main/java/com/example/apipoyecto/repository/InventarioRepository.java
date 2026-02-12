package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}

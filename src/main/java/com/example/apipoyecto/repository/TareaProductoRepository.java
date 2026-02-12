package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.TareaProducto;
import com.example.apipoyecto.model.TareaProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaProductoRepository extends JpaRepository<TareaProducto, TareaProductoId> {

    List<TareaProducto> findByTrabajoId(Long trabajoId);

    List<TareaProducto> findByProductoId(Long productoId);
}

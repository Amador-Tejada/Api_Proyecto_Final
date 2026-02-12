package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.TareaProducto;
import com.example.apipoyecto.model.TareaProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Repositorio para la entidad TareaProducto, que extiende JpaRepository para proporcionar métodos CRUD.
 * Además, se definen métodos personalizados para buscar tareas por trabajoId y productoId.
 */
@Repository
public interface TareaProductoRepository extends JpaRepository<TareaProducto, TareaProductoId> {

    List<TareaProducto> findByTrabajoId(Long trabajoId);

    List<TareaProducto> findByProductoId(Long productoId);
}

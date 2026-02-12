package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Repositorio para la entidad Inventario, que extiende JpaRepository para proporcionar métodos CRUD.
 * No se definen métodos personalizados en este caso, ya que los métodos básicos de JpaRepository
 * son suficientes para las operaciones de inventario.
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}

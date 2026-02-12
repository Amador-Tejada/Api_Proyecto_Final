package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* Repositorio para la entidad Cliente, que extiende JpaRepository para proporcionar métodos CRUD.
 * Además, se definen métodos personalizados para buscar clientes por su correo electrónico y verificar
 * su existencia.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

    boolean existsByCorreoElectronico(String correoElectronico);
}

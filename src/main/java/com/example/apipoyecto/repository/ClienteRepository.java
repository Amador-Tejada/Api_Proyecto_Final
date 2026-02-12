package com.example.apipoyecto.repository;


import com.example.apipoyecto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

    boolean existsByCorreoElectronico(String correoElectronico);
}

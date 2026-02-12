package com.example.apipoyecto.controller;

import com.example.apipoyecto.model.Trabajo;
import com.example.apipoyecto.repository.ClienteRepository;
import com.example.apipoyecto.repository.TrabajadorRepository;
import com.example.apipoyecto.repository.TrabajoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar trabajos.
 * Proporciona endpoints para crear, leer y eliminar trabajos.
 *
 * Cada trabajo está asociado a un cliente y un trabajador, por lo que se valida que ambos existan antes de crear un nuevo trabajo.
 */

@RestController
@RequestMapping("/api/trabajos")
@RequiredArgsConstructor
public class TrabajoController {

    private final TrabajoRepository trabajoRepository;
    private final ClienteRepository clienteRepository;
    private final TrabajadorRepository trabajadorRepository;

    @GetMapping
    public List<Trabajo> listar() {
        return trabajoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajo> obtener(@PathVariable Long id) {
        return trabajoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Trabajo> crear(@Valid @RequestBody Trabajo trabajo) {

        if (trabajo.getCliente() == null || trabajo.getTrabajador() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(trabajoRepository.save(trabajo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!trabajoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        trabajoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


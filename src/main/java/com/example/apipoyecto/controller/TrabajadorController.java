package com.example.apipoyecto.controller;

import com.example.apipoyecto.model.Trabajador;
import com.example.apipoyecto.repository.TrabajadorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** * Controlador REST para gestionar trabajadores.
 * Proporciona endpoints para crear, leer y eliminar trabajadores. */
@RestController
@RequestMapping("/api/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorRepository trabajadorRepository;

    @GetMapping
    public List<Trabajador> listar() {
        return trabajadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> obtener(@PathVariable Long id) {
        return trabajadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trabajador crear(@Valid @RequestBody Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!trabajadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        trabajadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

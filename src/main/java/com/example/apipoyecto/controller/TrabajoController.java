package com.example.apipoyecto.controller;

import com.example.apipoyecto.model.Trabajo;
import com.example.apipoyecto.repository.ClienteRepository;
import com.example.apipoyecto.repository.TrabajadorRepository;
import com.example.apipoyecto.repository.TrabajoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Trabajo> crear(@RequestBody Trabajo trabajo) {

        if (trabajo.getCliente() == null || trabajo.getTrabajador() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Si solo envían los IDs pero no los objetos completos, puede dar error 500 por validación
        if (trabajo.getCliente().getId() != null) {
            clienteRepository.findById(trabajo.getCliente().getId()).ifPresent(trabajo::setCliente);
        }
        if (trabajo.getTrabajador().getId() != null) {
            trabajadorRepository.findById(trabajo.getTrabajador().getId()).ifPresent(trabajo::setTrabajador);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(trabajoRepository.save(trabajo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabajo> crearOActualizar(@PathVariable Long id, @RequestBody Trabajo nuevoTrabajo) {
        if (nuevoTrabajo.getCliente() != null && nuevoTrabajo.getCliente().getId() != null) {
            clienteRepository.findById(nuevoTrabajo.getCliente().getId()).ifPresent(nuevoTrabajo::setCliente);
        }
        if (nuevoTrabajo.getTrabajador() != null && nuevoTrabajo.getTrabajador().getId() != null) {
            trabajadorRepository.findById(nuevoTrabajo.getTrabajador().getId()).ifPresent(nuevoTrabajo::setTrabajador);
        }

        return trabajoRepository.findById(id).map(trabajoExistente -> {
            if (nuevoTrabajo.getTitulo() != null) trabajoExistente.setTitulo(nuevoTrabajo.getTitulo());
            if (nuevoTrabajo.getDescripcion() != null) trabajoExistente.setDescripcion(nuevoTrabajo.getDescripcion());
            if (nuevoTrabajo.getFechaProgramada() != null) trabajoExistente.setFechaProgramada(nuevoTrabajo.getFechaProgramada());
            if (nuevoTrabajo.getEstado() != null) trabajoExistente.setEstado(nuevoTrabajo.getEstado());
            if (nuevoTrabajo.getPrioridad() != null) trabajoExistente.setPrioridad(nuevoTrabajo.getPrioridad());
            if (nuevoTrabajo.getCliente() != null) trabajoExistente.setCliente(nuevoTrabajo.getCliente());
            if (nuevoTrabajo.getTrabajador() != null) trabajoExistente.setTrabajador(nuevoTrabajo.getTrabajador());

            return ResponseEntity.ok(trabajoRepository.save(trabajoExistente));
        }).orElseGet(() -> {
            nuevoTrabajo.setId(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(trabajoRepository.save(nuevoTrabajo));
        });
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Trabajo> actualizar(@PathVariable Long id, @RequestBody Trabajo trabajoActualizado) {
        return trabajoRepository.findById(id).map(trabajo -> {
            if (trabajoActualizado.getTitulo() != null) {
                trabajo.setTitulo(trabajoActualizado.getTitulo());
            }
            if (trabajoActualizado.getDescripcion() != null) {
                trabajo.setDescripcion(trabajoActualizado.getDescripcion());
            }
            if (trabajoActualizado.getFechaProgramada() != null) {
                trabajo.setFechaProgramada(trabajoActualizado.getFechaProgramada());
            }
            if (trabajoActualizado.getEstado() != null) {
                trabajo.setEstado(trabajoActualizado.getEstado());
            }
            if (trabajoActualizado.getPrioridad() != null) {
                trabajo.setPrioridad(trabajoActualizado.getPrioridad());
            }
            if (trabajoActualizado.getCliente() != null) {
                if (trabajoActualizado.getCliente().getId() != null) {
                    clienteRepository.findById(trabajoActualizado.getCliente().getId()).ifPresent(trabajo::setCliente);
                } else {
                    trabajo.setCliente(trabajoActualizado.getCliente());
                }
            }
            if (trabajoActualizado.getTrabajador() != null) {
                if (trabajoActualizado.getTrabajador().getId() != null) {
                    trabajadorRepository.findById(trabajoActualizado.getTrabajador().getId()).ifPresent(trabajo::setTrabajador);
                } else {
                    trabajo.setTrabajador(trabajoActualizado.getTrabajador());
                }
            }
            return ResponseEntity.ok(trabajoRepository.save(trabajo));
        }).orElse(ResponseEntity.notFound().build());
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

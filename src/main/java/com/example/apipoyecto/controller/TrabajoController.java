package com.example.apipoyecto.controller;

import com.example.apipoyecto.dto.TrabajoDTO;
import com.example.apipoyecto.mapper.TrabajoMapper;
import com.example.apipoyecto.model.Cliente;
import com.example.apipoyecto.model.EstadoTrabajo;
import com.example.apipoyecto.model.PrioridadTrabajo;
import com.example.apipoyecto.model.Trabajador;
import com.example.apipoyecto.model.Trabajo;
import com.example.apipoyecto.repository.ClienteRepository;
import com.example.apipoyecto.repository.TrabajadorRepository;
import com.example.apipoyecto.repository.TrabajoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestionar trabajos.
 * Proporciona endpoints para crear, leer, actualizar y eliminar trabajos.
 *
 * - Un trabajo SIEMPRE está asociado a un cliente existente.
 * - Un trabajo PUEDE NO tener trabajador asignado (opcional).
 */
@RestController
@RequestMapping("/api/trabajos")
@RequiredArgsConstructor
public class TrabajoController {

    private final TrabajoRepository trabajoRepository;
    private final ClienteRepository clienteRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final TrabajoMapper trabajoMapper;

    @GetMapping
    public List<TrabajoDTO> listar() {
        return trabajoRepository.findAll().stream()
                .map(trabajoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajoDTO> obtener(@PathVariable Long id) {
        return trabajoRepository.findById(id)
                .map(trabajo -> ResponseEntity.ok(trabajoMapper.toDTO(trabajo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody TrabajoDTO dto) {
        try {
            Trabajo trabajo = new Trabajo();
            trabajo.setTitulo(dto.getTitulo());
            trabajo.setDescripcion(dto.getDescripcion());
            trabajo.setFechaProgramada(dto.getFechaProgramada());

            // Convertir enums
            if (dto.getEstado() != null) {
                trabajo.setEstado(EstadoTrabajo.valueOf(dto.getEstado()));
            }
            if (dto.getPrioridad() != null) {
                trabajo.setPrioridad(PrioridadTrabajo.valueOf(dto.getPrioridad()));
            }

            // Buscar cliente (obligatorio)
            if (dto.getClienteId() != null) {
                Cliente cliente = clienteRepository.findById(dto.getClienteId())
                        .orElse(null);
                if (cliente == null) {
                    return ResponseEntity.badRequest()
                            .body("Cliente no encontrado con ID: " + dto.getClienteId());
                }
                trabajo.setCliente(cliente);
            } else {
                return ResponseEntity.badRequest()
                        .body("El cliente es obligatorio");
            }

            // Buscar trabajador si se proporciona ID (opcional)
            if (dto.getTrabajadorId() != null) {
                Trabajador trabajador = trabajadorRepository.findById(dto.getTrabajadorId())
                        .orElse(null);
                if (trabajador == null) {
                    return ResponseEntity.badRequest()
                            .body("Trabajador no encontrado con ID: " + dto.getTrabajadorId());
                }
                trabajo.setTrabajador(trabajador);
            }

            Trabajo guardado = trabajoRepository.save(trabajo);
            return ResponseEntity.ok(trabajoMapper.toDTO(guardado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Valor inválido para estado o prioridad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody TrabajoDTO dto) {
        return trabajoRepository.findById(id)
                .map(trabajo -> {
                    try {
                        trabajo.setTitulo(dto.getTitulo());
                        trabajo.setDescripcion(dto.getDescripcion());
                        trabajo.setFechaProgramada(dto.getFechaProgramada());

                        // Actualizar enums
                        if (dto.getEstado() != null) {
                            trabajo.setEstado(EstadoTrabajo.valueOf(dto.getEstado()));
                        }
                        if (dto.getPrioridad() != null) {
                            trabajo.setPrioridad(PrioridadTrabajo.valueOf(dto.getPrioridad()));
                        }

                        // Actualizar cliente (obligatorio)
                        if (dto.getClienteId() != null) {
                            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                                    .orElse(null);
                            if (cliente == null) {
                                return ResponseEntity.badRequest()
                                        .body("Cliente no encontrado con ID: " + dto.getClienteId());
                            }
                            trabajo.setCliente(cliente);
                        }

                        // Actualizar trabajador (opcional)
                        if (dto.getTrabajadorId() != null) {
                            Trabajador trabajador = trabajadorRepository.findById(dto.getTrabajadorId())
                                    .orElse(null);
                            if (trabajador == null) {
                                return ResponseEntity.badRequest()
                                        .body("Trabajador no encontrado con ID: " + dto.getTrabajadorId());
                            }
                            trabajo.setTrabajador(trabajador);
                        } else {
                            trabajo.setTrabajador(null);
                        }

                        Trabajo actualizado = trabajoRepository.save(trabajo);
                        return ResponseEntity.ok(trabajoMapper.toDTO(actualizado));
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest()
                                .body("Valor inválido para estado o prioridad: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
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


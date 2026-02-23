package com.example.apipoyecto.mapper;

import com.example.apipoyecto.dto.TrabajoDTO;
import com.example.apipoyecto.model.*;
import com.example.apipoyecto.repository.ClienteRepository;
import com.example.apipoyecto.repository.TrabajadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre Trabajo (entidad) y TrabajoDTO.
 */
@Component
@RequiredArgsConstructor
public class TrabajoMapper {

    private final ClienteMapper clienteMapper;
    private final TrabajadorMapper trabajadorMapper;
    private final ClienteRepository clienteRepository;
    private final TrabajadorRepository trabajadorRepository;

    /**
     * Convierte una entidad Trabajo a TrabajoDTO.
     */
    public TrabajoDTO toDTO(Trabajo trabajo) {
        if (trabajo == null) {
            return null;
        }

        return TrabajoDTO.builder()
                .id(trabajo.getId())
                .titulo(trabajo.getTitulo())
                .descripcion(trabajo.getDescripcion())
                .fechaProgramada(trabajo.getFechaProgramada())
                .estado(trabajo.getEstado() != null ? trabajo.getEstado().name() : null)
                .prioridad(trabajo.getPrioridad() != null ? trabajo.getPrioridad().name() : null)
                .clienteId(trabajo.getCliente() != null ? trabajo.getCliente().getId() : null)
                .trabajadorId(trabajo.getTrabajador() != null ? trabajo.getTrabajador().getId() : null)
                .build();
    }

    /**
     * Convierte un TrabajoDTO a entidad Trabajo.
     * Busca las relaciones en la base de datos usando los IDs proporcionados.
     */
    public Trabajo toEntity(TrabajoDTO dto) {
        if (dto == null) {
            return null;
        }

        Trabajo trabajo = Trabajo.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .fechaProgramada(dto.getFechaProgramada())
                .build();

        // Convertir estado
        if (dto.getEstado() != null) {
            trabajo.setEstado(EstadoTrabajo.valueOf(dto.getEstado()));
        }

        // Convertir prioridad
        if (dto.getPrioridad() != null) {
            trabajo.setPrioridad(PrioridadTrabajo.valueOf(dto.getPrioridad()));
        }

        // Buscar cliente por ID
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getClienteId()));
            trabajo.setCliente(cliente);
        }

        // Buscar trabajador por ID (opcional)
        if (dto.getTrabajadorId() != null) {
            Trabajador trabajador = trabajadorRepository.findById(dto.getTrabajadorId())
                    .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + dto.getTrabajadorId()));
            trabajo.setTrabajador(trabajador);
        }

        return trabajo;
    }

    /**
     * Actualiza una entidad Trabajo existente con los datos del DTO.
     */
    public void updateEntityFromDTO(TrabajoDTO dto, Trabajo trabajo) {
        if (dto == null || trabajo == null) {
            return;
        }

        trabajo.setTitulo(dto.getTitulo());
        trabajo.setDescripcion(dto.getDescripcion());
        trabajo.setFechaProgramada(dto.getFechaProgramada());

        // Actualizar estado
        if (dto.getEstado() != null) {
            trabajo.setEstado(EstadoTrabajo.valueOf(dto.getEstado()));
        }

        // Actualizar prioridad
        if (dto.getPrioridad() != null) {
            trabajo.setPrioridad(PrioridadTrabajo.valueOf(dto.getPrioridad()));
        }

        // Actualizar cliente
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getClienteId()));
            trabajo.setCliente(cliente);
        }

        // Actualizar trabajador (opcional)
        if (dto.getTrabajadorId() != null) {
            Trabajador trabajador = trabajadorRepository.findById(dto.getTrabajadorId())
                    .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + dto.getTrabajadorId()));
            trabajo.setTrabajador(trabajador);
        } else {
            trabajo.setTrabajador(null);
        }
    }
}


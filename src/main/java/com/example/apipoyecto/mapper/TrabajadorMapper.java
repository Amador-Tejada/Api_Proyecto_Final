package com.example.apipoyecto.mapper;

import com.example.apipoyecto.dto.TrabajadorDTO;
import com.example.apipoyecto.model.Trabajador;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre Trabajador (entidad) y TrabajadorDTO.
 */
@Component
public class TrabajadorMapper {

    /**
     * Convierte una entidad Trabajador a TrabajadorDTO.
     */
    public TrabajadorDTO toDTO(Trabajador trabajador) {
        if (trabajador == null) {
            return null;
        }

        return TrabajadorDTO.builder()
                .id(trabajador.getId())
                .nombre(trabajador.getNombre())
                .puesto(trabajador.getPuesto())
                .build();
    }

    /**
     * Convierte un TrabajadorDTO a entidad Trabajador.
     */
    public Trabajador toEntity(TrabajadorDTO dto) {
        if (dto == null) {
            return null;
        }

        return Trabajador.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .puesto(dto.getPuesto())
                .build();
    }

    /**
     * Actualiza una entidad Trabajador existente con los datos del DTO.
     */
    public void updateEntityFromDTO(TrabajadorDTO dto, Trabajador trabajador) {
        if (dto == null || trabajador == null) {
            return;
        }

        trabajador.setNombre(dto.getNombre());
        trabajador.setPuesto(dto.getPuesto());
    }
}


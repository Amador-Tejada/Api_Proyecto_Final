package com.example.apipoyecto.mapper;

import com.example.apipoyecto.dto.ClienteDTO;
import com.example.apipoyecto.model.Cliente;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre Cliente (entidad) y ClienteDTO.
 */
@Component
public class ClienteMapper {

    /**
     * Convierte una entidad Cliente a ClienteDTO.
     */
    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .correoElectronico(cliente.getCorreoElectronico())
                .build();
    }

    /**
     * Convierte un ClienteDTO a entidad Cliente.
     */
    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .correoElectronico(dto.getCorreoElectronico())
                .build();
    }

    /**
     * Actualiza una entidad Cliente existente con los datos del DTO.
     */
    public void updateEntityFromDTO(ClienteDTO dto, Cliente cliente) {
        if (dto == null || cliente == null) {
            return;
        }

        cliente.setNombre(dto.getNombre());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setCorreoElectronico(dto.getCorreoElectronico());
    }
}


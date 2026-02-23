package com.example.apipoyecto.mapper;

import com.example.apipoyecto.dto.ProductoDTO;
import com.example.apipoyecto.model.Producto;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre Producto (entidad) y ProductoDTO.
 */
@Component
public class ProductoMapper {

    /**
     * Convierte una entidad Producto a ProductoDTO.
     */
    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .unidadMedida(producto.getUnidadMedida())
                .build();
    }

    /**
     * Convierte un ProductoDTO a entidad Producto.
     */
    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .unidadMedida(dto.getUnidadMedida())
                .build();
    }

    /**
     * Actualiza una entidad Producto existente con los datos del DTO.
     */
    public void updateEntityFromDTO(ProductoDTO dto, Producto producto) {
        if (dto == null || producto == null) {
            return;
        }

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setUnidadMedida(dto.getUnidadMedida());
    }
}


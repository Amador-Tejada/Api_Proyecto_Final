package com.example.apipoyecto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Inventario - usado en las peticiones y respuestas de la API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioDTO {

    private Long id;

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @PositiveOrZero(message = "La cantidad debe ser cero o mayor")
    private Double cantidadDisponible;

    @NotNull(message = "La cantidad mínima es obligatoria")
    @PositiveOrZero(message = "La cantidad mínima debe ser cero o mayor")
    private Double cantidadMinima;

    @Size(max = 200, message = "La ubicación no puede exceder 200 caracteres")
    private String ubicacion;

    // Campo para incluir información del producto en respuestas
    private ProductoDTO producto;
}


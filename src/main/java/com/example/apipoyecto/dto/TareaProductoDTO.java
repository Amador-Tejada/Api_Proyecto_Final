package com.example.apipoyecto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para TareaProducto - usado en las peticiones y respuestas de la API.
 * Representa la relación entre un trabajo y un producto con su cantidad.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaProductoDTO {

    @NotNull(message = "El trabajo es obligatorio")
    private Long trabajoId;

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidad;

    // Campos para incluir información completa en respuestas
    private ProductoDTO producto;
    private String trabajoTitulo;
}


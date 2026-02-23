package com.example.apipoyecto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para Trabajo - usado en las peticiones de la API.
 * Utiliza IDs para las relaciones en lugar de objetos completos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajoDTO {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 200, message = "El título debe tener entre 3 y 200 caracteres")
    private String titulo;

    @Size(max = 5000, message = "La descripción no puede exceder 5000 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha programada es obligatoria")
    private LocalDate fechaProgramada;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "La prioridad es obligatoria")
    private String prioridad;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    // Trabajador es opcional
    private Long trabajadorId;
}


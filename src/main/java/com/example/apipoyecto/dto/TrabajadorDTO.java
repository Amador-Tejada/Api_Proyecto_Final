package com.example.apipoyecto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Trabajador - usado en las peticiones y respuestas de la API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajadorDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El puesto es obligatorio")
    @Size(min = 2, max = 100, message = "El puesto debe tener entre 2 y 100 caracteres")
    private String puesto;
}


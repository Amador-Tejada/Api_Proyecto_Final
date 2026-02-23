package com.example.apipoyecto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Cliente - usado en las peticiones y respuestas de la API.
 * Evita exponer la entidad directamente y permite validaciones específicas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[+]?[0-9\\s().-]{7,20}$", message = "El teléfono debe contener entre 7 y 20 caracteres (números, espacios, paréntesis, guiones o puntos)")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 1, max = 200, message = "La dirección debe tener entre 1 y 200 caracteres")
    private String direccion;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido (ejemplo: usuario@dominio.com)")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo electrónico debe tener un formato válido")
    private String correoElectronico;
}


package com.example.apipoyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
  * Entidad que representa a un trabajador en el sistema.
  * Un trabajador puede tener múltiples trabajos asignados. */
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El puesto es obligatorio")
    @Size(min = 2, max = 100, message = "El puesto debe tener entre 2 y 100 caracteres")
    private String puesto;

    @OneToMany(mappedBy = "trabajador")
    @JsonIgnore
    @Valid
    @Builder.Default
    private List<Trabajo> trabajos = new ArrayList<>();
}

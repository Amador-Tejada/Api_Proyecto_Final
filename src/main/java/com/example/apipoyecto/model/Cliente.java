package com.example.apipoyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[+]?[(]?[0-9]{1,4}[)]?[-\\s./0-9]*$", message = "Teléfono con formato inválido")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 1, max = 200, message = "La dirección debe tener entre 1 y 200 caracteres")
    private String direccion;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Correo electrónico con formato inválido")
    @Column(unique = true)
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    @Valid
    @Builder.Default
    private List<Trabajo> trabajos = new ArrayList<>();
}
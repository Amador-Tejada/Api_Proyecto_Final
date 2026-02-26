package com.example.apipoyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Para crear la entidad Cliente se han definido los atributos y con LOMBOK
 * se han generado los getters, setters, constructores y el builder.
 * Se han aplicado validaciones a cada campo para asegurar que los datos ingresados sean correctos.
 * La relación con la entidad Trabajo se ha establecido como OneToMany, indicando que un cliente puede tener múltiples trabajos.
 *
 * Se ha utilizado @JsonIgnore para evitar problemas de serialización al convertir a JSON.
 *
 * LOMBOK se utiliza para reducir codigo repetitivo, como getters, setters, constructores,
 * lo que hace que el código sea más limpio y fácil de mantener.
 */



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
    @Pattern(regexp = "^[+]?[0-9\\s().-]{7,20}$", message = "El teléfono debe contener entre 7 y 20 caracteres (números, espacios, paréntesis, guiones o puntos)")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 1, max = 200, message = "La dirección debe tener entre 1 y 200 caracteres")
    private String direccion;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido (ejemplo: usuario@dominio.com)")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo electrónico debe tener un formato válido")
    @Column(unique = true)
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Trabajo> trabajos = new ArrayList<>();
}
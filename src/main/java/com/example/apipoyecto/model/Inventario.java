package com.example.apipoyecto.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Inventario {

    @Id
    private Long id;

    @NotNull(message = "El producto es obligatorio")
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @Valid
    private Producto producto;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @PositiveOrZero(message = "La cantidad disponible debe ser mayor o igual a cero")
    private Double cantidadDisponible;
}


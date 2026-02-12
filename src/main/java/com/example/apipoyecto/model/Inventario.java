package com.example.apipoyecto.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Clase que representa el inventario de productos disponibles para los trabajos.
 * Cada registro en esta tabla corresponde a un producto específico y la cantidad disponible en el inventario.
 * Esta clase se relaciona con la clase Producto a través de una relación uno a uno, utilizando el mismo ID.
 * La cantidad disponible debe ser un valor positivo o cero, indicando cuántas unidades del producto están disponibles para su uso en los trabajos.
 */


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


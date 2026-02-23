package com.example.apipoyecto.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Clase que representa la relación entre una tarea (trabajo) y un producto,
 * indicando la cantidad de producto necesaria para completar la tarea.
 * Utiliza una clave compuesta (TareaProductoId) para identificar
 * de manera única cada relación entre trabajo y producto.
 *
 * Incluye validaciones para asegurar que los campos obligatorios estén presentes y que la cantidad sea positiva. */


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TareaProducto {

    @NotNull(message = "El ID de la tarea producto es obligatorio")
    @EmbeddedId
    private TareaProductoId id;

    @NotNull(message = "El trabajo es obligatorio")
    @ManyToOne
    @MapsId("trabajoId")
    @JoinColumn(name = "trabajo_id")
    private Trabajo trabajo;

    @NotNull(message = "El producto es obligatorio")
    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidad;
}


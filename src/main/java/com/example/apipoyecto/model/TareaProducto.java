package com.example.apipoyecto.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TareaProducto {

    @NotNull(message = "El ID de la tarea producto es obligatorio")
    @EmbeddedId
    @Valid
    private TareaProductoId id;

    @NotNull(message = "El trabajo es obligatorio")
    @ManyToOne
    @MapsId("trabajoId")
    @JoinColumn(name = "trabajo_id")
    @Valid
    private Trabajo trabajo;

    @NotNull(message = "El producto es obligatorio")
    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    @Valid
    private Producto producto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidad;
}


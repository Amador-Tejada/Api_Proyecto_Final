package com.example.apipoyecto.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Clase que representa la clave compuesta para la entidad TareaProducto.
 * Esta clase se utiliza para mapear la relación entre Trabajo y Producto. */
@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class TareaProductoId implements java.io.Serializable {
    private Long trabajoId;
    private Long productoId;
}

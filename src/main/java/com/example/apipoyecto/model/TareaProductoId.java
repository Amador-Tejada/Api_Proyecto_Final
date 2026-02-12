package com.example.apipoyecto.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TareaProductoId implements java.io.Serializable {

    private Long trabajoId;
    private Long productoId;
}

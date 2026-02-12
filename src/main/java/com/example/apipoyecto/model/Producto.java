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
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    @Size(max = 2000, message = "La descripción no puede exceder 2000 caracteres")
    private String descripcion;

    @NotBlank(message = "La unidad de medida es obligatoria")
    @Size(min = 1, max = 50, message = "La unidad de medida debe tener entre 1 y 50 caracteres")
    private String unidadMedida;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    @Valid
    private Inventario inventario;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    @Valid
    @Builder.Default
    private List<TareaProducto> tareasProductos = new ArrayList<>();
}


package com.example.apipoyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un trabajo a realizar.
 * Un trabajo está asociado a un cliente, un trabajador y puede tener múltiples tareas/productos relacionados.
 */
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Trabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 200, message = "El título debe tener entre 3 y 200 caracteres")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    @Size(max = 5000, message = "La descripción no puede exceder 5000 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha programada es obligatoria")
    private java.time.LocalDate fechaProgramada;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private EstadoTrabajo estado;

    @NotNull(message = "La prioridad es obligatoria")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private PrioridadTrabajo prioridad;

    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne
    @Valid
    private Cliente cliente;

    @NotNull(message = "El trabajador es obligatorio")
    @ManyToOne
    @Valid
    private Trabajador trabajador;

    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL)
    @JsonIgnore
    @Valid
    @Builder.Default
    private List<TareaProducto> tareasProductos = new ArrayList<>();
}

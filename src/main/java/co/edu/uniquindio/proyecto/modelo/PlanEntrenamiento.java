package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPlanEntrenamiento;

    @Column(nullable = false, length = 100)
    private String nombre;

    private int duracion;

    @Column(nullable = false)
    private String dificultad;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "Entrenador_Usuario_codigo")
    private Entrenador entrenador;

    @ManyToOne
    @JoinColumn(name = "TipoEntrenamiento_codTipo")
    private TipoEntrenamiento tipoEntrenamiento;
}

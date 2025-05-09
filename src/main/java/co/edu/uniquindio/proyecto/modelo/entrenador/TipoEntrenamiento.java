package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "tipoEntrenamiento")
    private List<PlanEntrenamiento> planes;
}

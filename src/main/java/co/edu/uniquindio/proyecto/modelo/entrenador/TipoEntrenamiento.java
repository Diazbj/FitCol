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
    private Long codTipo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "tipoEntrenamiento")
    private List<PlanEntrenamiento> planes;
}

package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RutinaPlanEntId.class)
public class RutinaPlanEnt {

    @Id
    @ManyToOne
    @JoinColumn(name = "Rutina_codRutina")
    private Rutina rutina;

    @Id
    @ManyToOne
    @JoinColumn(name = "PlanEntrenamiento_codPlanEntrenamiento")
    private PlanEntrenamiento planEntrenamiento;
}
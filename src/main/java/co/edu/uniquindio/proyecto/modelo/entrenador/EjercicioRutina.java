package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EjercicioRutinaId.class)
public class EjercicioRutina {

    @Id
    @ManyToOne
    @JoinColumn(name = "Rutina_codRutina")
    private Rutina rutina;

    @Id
    @ManyToOne
    @JoinColumn(name = "Ejercicio_codEjercicio")
    private Ejercicio ejercicio;

    private Integer numeroSeries;
    private Integer numeroRepeticiones;
}

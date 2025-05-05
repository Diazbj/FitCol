package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codEjercicio;

    private String nombre;

    @Lob
    private String descripcion;

    @OneToMany(mappedBy = "ejercicio")
    private List<EjercicioRutina> ejerciciosRutina;
}

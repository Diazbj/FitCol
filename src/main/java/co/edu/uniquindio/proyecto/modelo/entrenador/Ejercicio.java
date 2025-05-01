package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
    private Long codEjercicio;

    private String nombre;

    @Lob
    private String descripcion;

    @OneToMany(mappedBy = "ejercicio")
    private List<EjercicioRutina> ejerciciosRutina;
}

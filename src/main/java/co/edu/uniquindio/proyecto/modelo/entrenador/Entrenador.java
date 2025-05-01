package co.edu.uniquindio.proyecto.modelo.entrenador;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Entrenador extends Usuario {

    private Integer anosExp;

    @OneToMany(mappedBy = "entrenador")
    private List<PlanEntrenamiento> planes;

    @OneToMany(mappedBy = "entrenador")
    private List<AsesoriaEnt> asesorias;

    @OneToMany(mappedBy = "entrenador")
    private List<EntrenadorCertificacion> entrenadorCertificaciones;
}
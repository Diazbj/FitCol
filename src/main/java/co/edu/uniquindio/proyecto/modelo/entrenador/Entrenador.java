package co.edu.uniquindio.proyecto.modelo.entrenador;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.modelo.nutricionista.TituloUniversitario;
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

    private Integer aniosExp;

    @OneToMany(mappedBy = "entrenador")
    private List<PlanEntrenamiento> planes;

    @OneToMany(mappedBy = "entrenador")
    private List<AsesoriaEnt> asesorias;

    @ManyToMany
    @JoinTable(
            name = "EntrenadorCertificacion",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cod_certificacion")
    )
    private List<Certificacion> certificaciones;
}
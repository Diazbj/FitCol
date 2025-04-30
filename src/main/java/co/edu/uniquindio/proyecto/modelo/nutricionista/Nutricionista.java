package co.edu.uniquindio.proyecto.modelo.nutricionista;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nutricionista extends Usuario {


    @Column(nullable = false)
    private int aniosExp;

    @OneToMany(mappedBy = "nutricionista")
    private List<PlanAlimenticio> planes;

    @OneToMany(mappedBy = "nutricionista")
    private List<AsesoriaAlim> asesorias;

    @ManyToMany
    @JoinTable(
            name = "NutricionistaTitulo",
            joinColumns = @JoinColumn(name = "Nut_Usuario_codigo"),
            inverseJoinColumns = @JoinColumn(name = "codTituloUniver")
    )
    private List<TituloUniversitario> titulos;


}

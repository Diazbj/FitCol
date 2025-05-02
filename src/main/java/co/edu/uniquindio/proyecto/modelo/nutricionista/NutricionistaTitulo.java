package co.edu.uniquindio.proyecto.modelo.nutricionista;

import co.edu.uniquindio.proyecto.modelo.entrenador.Certificacion;
import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "NutricionistaTitulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutricionistaTitulo {
    @EmbeddedId
    private NutricionistaTituloId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Nutricionista nutricionista;

    @ManyToOne
    @MapsId("codTitulo")
    @JoinColumn(name = "cod_titulo")
    private TituloUniversitario titulo;

}

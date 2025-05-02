package co.edu.uniquindio.proyecto.modelo.nutricionista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TituloUniversitario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TituloUniversitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codTitulo;

    private String nombre;
    private String institucion;

    @ManyToMany(mappedBy = "titulos")
    private List<Nutricionista> nutricionistas;
}

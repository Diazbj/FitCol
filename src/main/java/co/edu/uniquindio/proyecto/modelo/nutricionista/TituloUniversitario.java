package co.edu.uniquindio.proyecto.modelo.nutricionista;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TituloUniversitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codTituloUniver;

    private String nombre;
    private String institucion;

    @ManyToMany(mappedBy = "titulos")
    private List<Nutricionista> nutricionistas;
}

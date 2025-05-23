package co.edu.uniquindio.proyecto.modelo.nutricionista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codComida;

    private String nombre;
    private Integer porcion;
    private Integer proteinas;
    private Integer carbohidratos;
    private Integer grasa;

    @OneToMany(mappedBy = "comida", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ComidaIngrediente> ingredientes;

    @OneToMany(mappedBy = "comida", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ComidaPlanAli> comidaPlanAli;
}
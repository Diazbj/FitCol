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
    private Long codComida;

    private String nombre;
    private Integer porcion;
    private Integer proteinas;
    private Integer carbohidratos;
    private Integer grasa;

    @OneToMany(mappedBy = "comida")
    private List<ComidaIngrediente> ingredientes;
}

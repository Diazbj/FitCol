package co.edu.uniquindio.proyecto.modelo.nutricionista;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    @Id
    private String nombre;

    private Double precioPromedio;

    @OneToMany(mappedBy = "ingrediente",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ComidaIngrediente> comidas;
}

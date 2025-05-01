package co.edu.uniquindio.proyecto.modelo.nutricionista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ComidaIngredienteId.class)
public class ComidaIngrediente {

    @Id
    @ManyToOne
    @JoinColumn(name = "Comida_codComida")
    private Comida comida;

    @Id
    @ManyToOne
    @JoinColumn(name = "Ingrediente_nombre")
    private Ingrediente ingrediente;
}

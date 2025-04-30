package co.edu.uniquindio.proyecto.modelo.nutricionista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComidaPlanAli {

    @EmbeddedId
    private ComidaPlanAliId id;

    @ManyToOne
    @MapsId("cod_plan_alimentacion") // nombre del atributo en ComidaPlanAliId
    @JoinColumn(name = "cod_plan_alimentacion")
    private PlanAlimenticio planAlimentacion;

    @ManyToOne
    @MapsId("cod_comida") // nombre del atributo en ComidaPlanAliId
    @JoinColumn(name = "cod_Comida")
    private Comida comida;

    private int caloriasDiarias;
}

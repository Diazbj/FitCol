package co.edu.uniquindio.proyecto.modelo.nutricionista;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ComidaPlanAliId {
    private Long cod_plan_alimenticio;
    private Long cod_comida;
}

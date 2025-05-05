package co.edu.uniquindio.proyecto.dto.planEntrenamiento;

import java.util.List;

public record AsignarRutinasDTO(

       Long codPlanEntrenamiento,
       List<Long>codigosRutinas
) {
}

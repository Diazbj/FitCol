package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaCompletaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.RutinaCompletaDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RutinaMapper {

    @Mapping(target = "codPlanEntrenamiento", expression = "java(obtenerCodPlan(rutina))")
    RutinaCompletaDTO toCompletaDTO(Rutina rutina);

    CrearRutinaCompletaDTO toDTO(Rutina rutina);

    Rutina fromCrearDTO(CrearRutinaCompletaDTO dto);

    // Método auxiliar para obtener el código del plan desde la tabla intermedia
    default Long obtenerCodPlan(Rutina rutina) {
        if (rutina.getRutinaPlanEnts() != null && !rutina.getRutinaPlanEnts().isEmpty()) {
            return rutina.getRutinaPlanEnts().get(0).getPlanEntrenamiento().getCodPlanEntrenamiento();
        }
        return null;
    }
}
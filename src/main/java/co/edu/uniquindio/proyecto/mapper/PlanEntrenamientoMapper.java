package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanEntrenamientoMapper {

    PlanEntrenamiento fromDTO(CrearPlanEntrenamientoDTO dto);
    PlanEntrenamientoDTO toDTO(PlanEntrenamiento plan);
}

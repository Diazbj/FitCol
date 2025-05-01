package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.entrenador.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PlanEntrenamientoMapper {
    @Mapping(target = "entrenador", ignore = true) // Se asigna en el servicio
    PlanEntrenamiento dtoToEntidad(CrearPlanEntrenamientoDTO crearPlanEntrenamientoDTO);

}

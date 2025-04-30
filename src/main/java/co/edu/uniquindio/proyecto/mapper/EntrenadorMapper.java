package co.edu.uniquindio.proyecto.mapper;


import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EntrenadorMapper {

    @Mapping(target = "entrenador", ignore = true)
    @Mapping(target = "tipoEntrenamiento", ignore = true)
    PlanEntrenamiento dtoToEntidad(CrearPlanEntrenamientoDTO dto);
}

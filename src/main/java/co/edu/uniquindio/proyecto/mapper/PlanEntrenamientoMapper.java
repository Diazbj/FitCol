package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanEntrenamientoMapper {

    PlanEntrenamiento fromDTO(CrearPlanEntrenamientoDTO dto);


    @Mapping(target = "codPlanEntrenamiento", source = "codPlanEntrenamiento") // 👈 importante
    @Mapping(target = "tipoEntrenamientoNombre", source = "tipoEntrenamiento.nombre")
    @Mapping(target = "tipoEntrenamientoId", source = "tipoEntrenamiento.id")
    @Mapping(target = "usuarioId", source = "entrenador.usuarioId")
    PlanEntrenamientoDTO toDTO(PlanEntrenamiento plan);
}

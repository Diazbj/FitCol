package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.planAlimenticio.CrearPlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.dto.planAlimenticio.PlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.PlanAlimenticio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanAlimenticioMapper {

    PlanAlimenticio fromDTO(CrearPlanAlimenticioDTO dto);
    @Mapping(target = "usuarioId", source = "nutricionista.usuarioId")
    PlanAlimenticioDTO toDTO(PlanAlimenticio plan);
}

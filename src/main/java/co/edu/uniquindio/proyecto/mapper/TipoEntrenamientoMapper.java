package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.TipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearTipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.TipoEntrenamiento;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TipoEntrenamientoMapper {

    TipoEntrenamientoDTO toDTO(TipoEntrenamiento tipo);

    TipoEntrenamiento fromCrearDTO(CrearTipoEntrenamientoDTO dto);
}

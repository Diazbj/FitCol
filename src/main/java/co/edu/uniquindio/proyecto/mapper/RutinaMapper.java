package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaCompletaDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RutinaMapper {
    Rutina fromCrearDTO(CrearRutinaCompletaDTO dto);
    CrearRutinaCompletaDTO toDTO(Rutina rutina);
}

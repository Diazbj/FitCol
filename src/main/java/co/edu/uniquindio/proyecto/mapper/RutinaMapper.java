package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.RutinaDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RutinaMapper {
    Rutina fromCrearDTO(CrearRutinaDTO dto);
    RutinaDTO toDTO(Rutina rutina);
}

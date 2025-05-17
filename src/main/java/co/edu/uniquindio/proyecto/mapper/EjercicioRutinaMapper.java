package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioRutinaDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.EjercicioRutina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EjercicioRutinaMapper {

    @Mapping(source = "ejercicio.codEjercicio", target = "codEjercicio") // Accedemos a codEjercicio de la entidad Ejercicio
    @Mapping(source = "ejercicio.nombre", target = "nombreEjercicio")
    EjercicioRutinaDTO toDTO(EjercicioRutina ejercicioRutina);
}

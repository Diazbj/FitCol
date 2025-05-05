package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.ejercicio.CrearEjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.Ejercicio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EjercicioMapper {
    Ejercicio fromCrearDTO(CrearEjercicioDTO dto);
    EjercicioDTO toDTO(Ejercicio ejercicio);
}

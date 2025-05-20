package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.ComidaIngrediente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComidaIngredienteMapper {

    @Mapping(source = "ingrediente.nombre", target = "ingrediente")
    @Mapping(source = "comida.codComida", target = "codComida")
    IngredienteComidaDTO toDTO(ComidaIngrediente comidaIngrediente);
}

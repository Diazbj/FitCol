package co.edu.uniquindio.proyecto.mapper;


import co.edu.uniquindio.proyecto.dto.ingrediente.CrearIngredienteDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Ingrediente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {

    Ingrediente fromCrearIngrediente(CrearIngredienteDTO crearIngredienteDTO);
    IngredienteDTO toDto(Ingrediente ingrediente);

}

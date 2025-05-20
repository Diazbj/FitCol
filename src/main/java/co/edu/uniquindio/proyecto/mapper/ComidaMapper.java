package co.edu.uniquindio.proyecto.mapper;


import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.CrearComidaDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Comida;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ComidaIngredienteMapper.class})
public interface ComidaMapper {

    Comida fromCrearComida(CrearComidaDTO crearComidaDTO);
    ComidaDTO toDto(Comida comida);
}

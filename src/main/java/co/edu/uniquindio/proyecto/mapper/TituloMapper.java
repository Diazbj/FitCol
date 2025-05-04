package co.edu.uniquindio.proyecto.mapper;



import co.edu.uniquindio.proyecto.dto.nutricionista.TituloDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.TituloUniversitario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TituloMapper {

    TituloUniversitario fromCrearDTOtoEntity(TituloDTO tituloDTO);
    // De entidad a DTO (este lo necesitas agregar)
    TituloDTO toDTO(TituloUniversitario tituloUniversitario);
}

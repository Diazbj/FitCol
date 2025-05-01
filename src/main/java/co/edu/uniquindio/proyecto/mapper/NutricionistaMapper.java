package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.nutricionista.CrearNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.EditarNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.NutricionistaDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Nutricionista;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NutricionistaMapper {

    @Mapping(target = "telefonos", source = "telefonos", qualifiedByName = "obtenerTelefonos")
    NutricionistaDTO fromEntityToDTO(Nutricionista usuario);

    @Mapping(target = "telefonos", ignore = true)// porque necesitas mapearlos aparte
    @Mapping(target = "estadoUsuario", constant = "INACTIVO")
    Nutricionista fromCrearDTOToEntity(CrearNutricionistaDTO crearNutricionistaDTO);

    @Named("obtenerTelefonos")
    default List<String> obtenerTelefonos(List<UsuarioTelefono> telefonos){
        return telefonos.stream().map(UsuarioTelefono::getNumero).toList();
    }

    @Mapping(target = "telefonos", ignore = true) // los telefonos se manejan aparte
    void actualizarNutricionistaDesdeDTO(EditarNutricionistaDTO dto, @MappingTarget Nutricionista usuario);

}

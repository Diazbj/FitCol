package co.edu.uniquindio.proyecto.mapper;


import co.edu.uniquindio.proyecto.dto.entrenador.EditarEntrenadorDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.EntrenadorDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.CrearEntrenadorDTO;

import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntrenadorMapper {

    @Mapping(target = "telefonos", source = "telefonos", qualifiedByName = "obtenerTelefonos")
    EntrenadorDTO fromEntityToDTO(Entrenador usuario);

    @Mapping(target = "telefonos", ignore = true)// porque necesitas mapearlos aparte
    @Mapping(target = "estadoUsuario", constant = "INACTIVO")
    Entrenador fromCrearDTOToEntity(CrearEntrenadorDTO crearEntrenadorDTO);

    @Named("obtenerTelefonos")
    default List<String> obtenerTelefonos(List<UsuarioTelefono> telefonos){
        return telefonos.stream().map(UsuarioTelefono::getNumero).toList();
    }

    @Mapping(target = "telefonos", ignore = true) // los telefonos se manejan aparte
    void actualizarEntrenadorDesdeDTO(EditarEntrenadorDTO dto, @MappingTarget Entrenador usuario);
}

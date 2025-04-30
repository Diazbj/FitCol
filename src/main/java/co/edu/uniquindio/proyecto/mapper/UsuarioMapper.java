package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public interface UsuarioMapper {

    @Mapping(target = "edad", source = "fechaNacimiento", qualifiedByName = "calcularEdad")
    @Mapping(target = "telefonos", source = "telefonos", qualifiedByName = "obtenerTelefonos")
    UsuarioDTO fromEntityToDTO(Usuario usuario);

    @Mapping(target = "fechaNacimiento", expression = "java(LocalDate.parse(crearUsuarioDTO.fechaNacimiento()))")
    @Mapping(target = "telefonos", ignore = true)// porque necesitas mapearlos aparte
    Usuario fromCrearDTOToEntity(CrearUsuarioDTO crearUsuarioDTO);

    @Named("calcularEdad")
    default int calcularEdad(LocalDate fechaNacimiento){
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Named("obtenerTelefonos")
    default List<String> obtenerTelefonos(List<UsuarioTelefono> telefonos){
        return telefonos.stream().map(UsuarioTelefono::getNumero).toList();
    }

    @Mapping(target = "telefonos", ignore = true) // los telefonos se manejan aparte
    void actualizarUsuarioDesdeDTO(EditarUsuarioDTO dto, @MappingTarget Usuario usuario);


}

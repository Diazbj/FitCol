package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public interface ClienteMapper {

    //@Mapping(target = "edad", source = "fechaNacimiento", qualifiedByName = "calcularEdad")
    @Mapping(target = "telefonos", source = "telefonos", qualifiedByName = "obtenerTelefonos")
    ClienteDTO fromEntityToDTO(Cliente usuario);

    @Mapping(target = "fechaNacimiento", expression = "java(LocalDate.parse(crearClienteDTO.fechaNacimiento()))")
    @Mapping(target = "telefonos", ignore = true)// porque necesitas mapearlos aparte
    @Mapping(target = "estadoUsuario", constant = "INACTIVO")
    @Mapping(target = "password", ignore = true)
    Cliente fromCrearDTOToEntity(CrearClienteDTO crearClienteDTO);

    @Named("calcularEdad")
    default int calcularEdad(LocalDate fechaNacimiento){
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Named("obtenerTelefonos")
    default List<String> obtenerTelefonos(List<UsuarioTelefono> telefonos){
        return telefonos.stream().map(UsuarioTelefono::getNumero).toList();
    }

    @Mapping(target = "telefonos", ignore = true) // los telefonos se manejan aparte
    void actualizarClienteDesdeDTO(EditarClienteDTO dto, @MappingTarget Cliente usuario);


}

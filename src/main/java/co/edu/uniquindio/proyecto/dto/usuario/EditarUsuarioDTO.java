package co.edu.uniquindio.proyecto.dto.usuario;

import java.util.List;



public record EditarUsuarioDTO(
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        String sexo,
        Integer peso,
        Integer altura,
        List<String> telefonos

) {

}



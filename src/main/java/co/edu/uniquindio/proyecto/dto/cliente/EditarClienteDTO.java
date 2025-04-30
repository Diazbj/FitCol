package co.edu.uniquindio.proyecto.dto.cliente;

import java.util.List;



public record EditarClienteDTO(
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



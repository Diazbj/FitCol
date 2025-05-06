package co.edu.uniquindio.proyecto.dto.cliente;

import java.util.List;



public record EditarClienteDTO(
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        String historialMedico,
        Integer peso,
        List<String> telefonos

) {

}



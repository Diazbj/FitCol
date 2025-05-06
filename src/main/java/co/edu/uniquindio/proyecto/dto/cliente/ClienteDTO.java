package co.edu.uniquindio.proyecto.dto.cliente;

import jakarta.validation.constraints.Email;
import java.util.List;

public record ClienteDTO(
        String usuarioId,
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        @Email String email,
        String sexo,
        String historialMedico,
        Integer edad,
        Integer peso,
        Integer altura,
        List<String> telefonos
){

}


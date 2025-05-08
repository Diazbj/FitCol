package co.edu.uniquindio.proyecto.dto.entrenador;

import jakarta.validation.constraints.Email;

import java.util.List;

public record EditarEntrenadorDTO(
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        Integer aniosExp,
        List<String> telefonos

) {
}

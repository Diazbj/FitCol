package co.edu.uniquindio.proyecto.dto.entrenador;

import jakarta.validation.constraints.Email;

import java.util.List;

public record EntrenadorDTO(

        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        @Email String email,
        List<String> telefonos
) {
}

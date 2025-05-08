package co.edu.uniquindio.proyecto.dto.nutricionista;

import jakarta.validation.constraints.Email;

import java.util.List;

public record NutricionistaDTO(

        String usuarioId,
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        Integer aniosExp,
        @Email String email,
        List<String> telefonos

) {
}

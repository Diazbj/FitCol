package co.edu.uniquindio.proyecto.dto.entrenador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CrearEntrenadorDTO(

        @NotBlank @Length(max = 100) String usuarioId,
        @NotBlank @Length(max = 100) String primerNombre,
        @NotBlank @Length(max = 100) String segundoNombre,
        @NotBlank @Length(max = 100) String primerApellido,
        @NotBlank @Length(max = 100) String segundoApellido,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 20) String password,
        Integer aniosExp,
        List<String> telefonos,
        String codCiudad

) {
}

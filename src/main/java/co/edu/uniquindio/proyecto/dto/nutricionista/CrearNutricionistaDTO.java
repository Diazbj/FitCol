package co.edu.uniquindio.proyecto.dto.nutricionista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CrearNutricionistaDTO(

        @NotBlank @Length(max = 100) String id,
        @NotBlank @Length(max = 100) String primerNombre,
        @NotBlank @Length(max = 100) String segundoNombre,
        @NotBlank @Length(min = 7, max = 20) String password,
        @NotBlank @Length(max = 100) String primerApellido,
        @NotBlank @Length(max = 100) String segundoApellido,
        @NotBlank @Length(max = 50)
        @Email String email,
        List<String> telefonos,
        Long codCiudad,
        Integer aniosExp,
        List<Long> codCertificaciones

) {
}

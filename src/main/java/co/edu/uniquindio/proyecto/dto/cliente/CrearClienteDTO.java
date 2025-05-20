package co.edu.uniquindio.proyecto.dto.cliente;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.List;

public record CrearClienteDTO(
        @NotBlank @Length(max = 100) String usuarioId,
        @NotBlank @Length(max = 100) String primerNombre,
        String segundoNombre,
        @NotBlank @Length(min = 7, max = 20) String password,
        @NotBlank @Length(max = 100) String primerApellido,
        @NotBlank @Length(max = 100) String segundoApellido,
        @NotBlank @Length(max = 100) String fechaNacimiento,
        @NotBlank @Length(max = 50)
        @Email String email,
        @NotBlank String sexo,
        List<String> telefonos,
        String codCiudad,
        String historialMedico,
        Integer peso,
        Integer altura
){

}
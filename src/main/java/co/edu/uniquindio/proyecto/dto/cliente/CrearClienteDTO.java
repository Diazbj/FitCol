package co.edu.uniquindio.proyecto.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CrearClienteDTO(
        @NotBlank @Length(max = 100) String id,
        @NotBlank @Length(max = 100) String nombre,
        @Length(min = 10, max = 20) String telefono,
        @NotBlank @Length(min = 7, max = 20) String password,
        @NotBlank @Length(max = 100) String primerApellido,
        @NotBlank @Length(max = 100) String segundoApellido,
        @NotBlank @Length(max = 100) String fechaNacimiento,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank String sexo,
        @NotBlank String historialMedico,
        Integer peso,
        Integer altura


) {
}

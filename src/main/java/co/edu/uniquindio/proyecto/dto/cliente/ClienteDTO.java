package co.edu.uniquindio.proyecto.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ClienteDTO(
        String id,
        String nombre,
        String telefono,
        String primerApellido,
        String segundoApellido,
        String email,
        String sexo,
        String historialMedico,
        Integer peso,
        Integer altura

) {
}

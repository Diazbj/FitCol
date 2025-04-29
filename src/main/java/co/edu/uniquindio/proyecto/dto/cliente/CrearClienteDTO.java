package co.edu.uniquindio.proyecto.dto.cliente;

import co.edu.uniquindio.proyecto.modelo.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CrearClienteDTO {

    @NotBlank
    @Length(max = 100)
    private String id;

    @NotBlank
    @Length(max = 100)
    private String nombre;

    @Length(min = 10, max = 20)
    private String telefono;

    @NotBlank
    @Length(min = 7, max = 20)
    private String password;

    @NotBlank
    @Length(max = 100)
    private String primerApellido;

    @NotBlank
    @Length(max = 100)
    private String segundoApellido;

    @NotBlank
    @Length(max = 100)
    private String fechaNacimiento;

    @NotBlank
    @Length(max = 50)
    @Email
    private String email;

    @NotBlank
    private String sexo;

    @NotBlank
    private String historialMedico;

    private Double peso;

    private Double altura;

    public CrearClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.telefono = cliente.getTelefono();
        this.password = cliente.getPassword();
        this.primerApellido = cliente.getPrimerApellido();
        this.segundoApellido = cliente.getSegundoApellido();
        this.fechaNacimiento = cliente.getFechaNacimiento().toString(); // asumiendo LocalDate
        this.email = cliente.getEmail();
        this.sexo = cliente.getSexo();
        this.historialMedico = cliente.getHistorialMedico();
        this.peso = cliente.getPeso();
        this.altura = cliente.getAltura();
    }
}

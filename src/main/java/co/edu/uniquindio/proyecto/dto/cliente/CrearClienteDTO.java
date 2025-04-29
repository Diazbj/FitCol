package co.edu.uniquindio.proyecto.dto.cliente;

import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CrearClienteDTO {

    @NotBlank
    @Length(max = 100)
    private String id;

    @NotBlank
    @Length(max = 100)
    private String primerNombre;

    @NotBlank
    @Length(max = 100)
    private String segundoNombre;



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

    private List<String> telefonos;

    public CrearClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.primerNombre=cliente.getPrimerNombre();
        this.segundoNombre=cliente.getSegundoNombre();
        this.password = cliente.getPassword();
        this.primerApellido = cliente.getPrimerApellido();
        this.segundoApellido = cliente.getSegundoApellido();
        this.fechaNacimiento = cliente.getFechaNacimiento().toString(); // asumiendo LocalDate
        this.email = cliente.getEmail();
        this.sexo = cliente.getSexo();
        this.historialMedico = cliente.getHistorialMedico();
        this.peso = cliente.getPeso();
        this.altura = cliente.getAltura();
        // ✅ Verificamos que la lista de teléfonos no sea null antes de procesarla
        if (cliente.getTelefonos() != null) {
            this.telefonos = cliente.getTelefonos()
                    .stream()
                    .map(UsuarioTelefono::getNumero)
                    .collect(Collectors.toList());
        } else {
            this.telefonos = new ArrayList<>(); // ✅ Evita NullPointerException
        }
    }
}

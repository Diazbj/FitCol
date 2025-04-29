package co.edu.uniquindio.proyecto.dto.cliente;

import jakarta.validation.constraints.Email;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class EditarClienteDTO {

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String sexo;
    private Integer peso;
    private Integer altura;



    public EditarClienteDTO(Cliente cliente) {
        this.primerNombre= cliente.getPrimerNombre();
        this.segundoNombre= cliente.getSegundoNombre();
        this.primerApellido = cliente.getPrimerApellido();
        this.segundoApellido = cliente.getSegundoApellido();
        this.sexo = cliente.getSexo();
        this.peso = cliente.getPeso() != null ? cliente.getPeso().intValue() : null;
        this.altura = cliente.getAltura() != null ? cliente.getAltura().intValue() : null;

    }
}
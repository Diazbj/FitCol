package co.edu.uniquindio.proyecto.dto.cliente;

import jakarta.validation.constraints.Email;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private String id;
    private String primernombre;
    private String segundonombre;
    private String primerApellido;
    private String segundoApellido;

    @Email
    private String email;

    private String sexo;
    private String historialMedico;
    private Integer edad;
    private Integer peso;
    private Integer altura;
    private List<String> telefonos;


    public ClienteDTO(Cliente cliente, int edadCalculada) {
        this.id = cliente.getId();
        this.primernombre = cliente.getPrimerNombre();
        this.segundonombre = cliente.getSegundoNombre();
        this.primerApellido = cliente.getPrimerApellido();
        this.segundoApellido = cliente.getSegundoApellido();
        this.email = cliente.getEmail();
        this.sexo = cliente.getSexo();
        this.historialMedico = cliente.getHistorialMedico();
        this.peso = cliente.getPeso() != null ? cliente.getPeso().intValue() : null;
        this.altura = cliente.getAltura() != null ? cliente.getAltura().intValue() : null;
        this.edad = edadCalculada;
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


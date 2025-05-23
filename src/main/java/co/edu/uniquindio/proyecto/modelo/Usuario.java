package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario {
    @Id
    private String usuarioId;

    private String primerNombre;
    private String segundoNombre;
    private String password;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioTelefono> telefonos = new ArrayList<>();
    private EstadoUsuario estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "codCiudad")
    private Ciudad ciudad;

}

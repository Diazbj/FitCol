package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Usuario {
    @Id
    private String id;

    private String primerNombre;
    private String segundoNombre;
    private String password;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String email;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioTelefono> telefonos = new ArrayList<>();


}

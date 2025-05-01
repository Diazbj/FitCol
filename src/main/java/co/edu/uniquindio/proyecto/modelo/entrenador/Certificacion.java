package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Certificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Certificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCertificacion;

    private String nombre;

    private String institucion;

    @OneToMany(mappedBy = "certificacion")
    private List<EntrenadorCertificacion> entrenadorCertificaciones;
}
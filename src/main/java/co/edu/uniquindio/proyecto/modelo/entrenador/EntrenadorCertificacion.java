package co.edu.uniquindio.proyecto.modelo.entrenador;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "EntrenadorCertificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntrenadorCertificacion {

    @EmbeddedId
    private EntrenadorCertificacionId id;

    @ManyToOne
    @MapsId("entrenadorCodigo")
    @JoinColumn(name = "Entrenador_Usuario_codigo")
    private Entrenador entrenador;

    @ManyToOne
    @MapsId("codCertificacion")
    @JoinColumn(name = "codCertificacion")
    private Certificacion certificacion;

    private LocalDate fechaAsignacion;
}

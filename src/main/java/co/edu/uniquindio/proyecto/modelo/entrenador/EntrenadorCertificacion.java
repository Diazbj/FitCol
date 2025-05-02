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
public class EntrenadorCertificacion {

    @EmbeddedId
    private EntrenadorCertificacionId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Entrenador entrenador;

    @ManyToOne
    @MapsId("codCertificacion")
    @JoinColumn(name = "cod_certificacion")
    private Certificacion certificacion;

}

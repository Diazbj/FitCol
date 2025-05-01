package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorCertificacionId implements Serializable {

    @Column(name = "codCertificacion")
    private Integer codCertificacion;

    @Column(name = "Entrenador_Usuario_codigo")
    private Integer entrenadorCodigo;
}

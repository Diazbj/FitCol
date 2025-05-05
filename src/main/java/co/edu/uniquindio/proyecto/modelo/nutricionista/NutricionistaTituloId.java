package co.edu.uniquindio.proyecto.modelo.nutricionista;


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
public class NutricionistaTituloId implements Serializable {

    @Column(name="cod_titulo")
    private Integer codTitulo;

    @Column(name="usuario_id")
    private String usuarioId;

}

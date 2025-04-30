package co.edu.uniquindio.proyecto.modelo.nutricionista;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesoriaAlimId implements Serializable {
    private Long nutricionista;
    private Long cliente;
}

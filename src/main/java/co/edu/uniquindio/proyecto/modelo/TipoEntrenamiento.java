package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEntrenamiento {

    @Id
    private Long codTipo;

    @Column(nullable = false, length = 50)
    private String nombre;
}

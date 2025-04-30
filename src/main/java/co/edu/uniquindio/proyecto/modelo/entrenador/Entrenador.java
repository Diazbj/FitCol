package co.edu.uniquindio.proyecto.modelo.entrenador;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Entrenador extends Usuario {

    @Column(nullable = false)
    private int aniosExp;

}
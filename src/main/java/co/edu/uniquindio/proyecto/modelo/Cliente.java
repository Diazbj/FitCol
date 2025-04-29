package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Cliente extends Usuario{
    private Double peso;
    private Double altura;
    private String sexo;
    private String historialMedico;
}

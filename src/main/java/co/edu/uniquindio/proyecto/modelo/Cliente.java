package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate fechaNacimiento;
}

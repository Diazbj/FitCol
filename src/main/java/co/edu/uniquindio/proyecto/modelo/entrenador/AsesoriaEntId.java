package co.edu.uniquindio.proyecto.modelo.entrenador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesoriaEntId {

    private Long entrenador;
    private Long cliente;
    private LocalDate fecha;
}

package co.edu.uniquindio.proyecto.modelo.entrenador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutinaPlanEntId implements Serializable {
    private Long rutina;
    private Long planEntrenamiento;
}
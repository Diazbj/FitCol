package co.edu.uniquindio.proyecto.dto.rutina;

import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioRutinaDTO;

import java.util.List;

public record CrearRutinaCompletaDTO(
        String nombre,
        Long codPlanEntrenamiento,
        List<EjercicioRutinaDTO> ejercicios
) {
}

package co.edu.uniquindio.proyecto.dto.ejercicio;

public record EjercicioRutinaDTO(
        Long codRutina,        // <- nuevo campo
        Long codEjercicio,
        Integer numeroSeries,
        Integer numeroRepeticiones,
        String nombreEjercicio
) {
}

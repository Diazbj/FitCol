package co.edu.uniquindio.proyecto.dto.planEntrenamiento;

public record PlanEntrenamientoDTO(
        String nombre,
        int duracion,
        String dificultad,
        String descripcion,
        Long entrenadorId,
        Long tipoEntrenamientoId
) {
}

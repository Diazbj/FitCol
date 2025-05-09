package co.edu.uniquindio.proyecto.dto.planEntrenamiento;

public record PlanEntrenamientoDTO(
        Long codPlanEntrenamiento,
        String nombre,
        int duracion,
        String dificultad,
        String descripcion,
        Long usuarioId,
        Long tipoEntrenamientoId,
        String tipoEntrenamientoNombre
) {
}

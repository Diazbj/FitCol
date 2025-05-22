package co.edu.uniquindio.proyecto.dto.progreso;

public record PlanesDeficitDTO(
        Long codPlanAlimenticio,
        String nombrePlan,
        Integer duracion,
        String objetivo,
        String usuarioId,
        String nombreNutricionista,
        Integer aniosExp,
        Integer caloriasTotalesPlan

) {
}

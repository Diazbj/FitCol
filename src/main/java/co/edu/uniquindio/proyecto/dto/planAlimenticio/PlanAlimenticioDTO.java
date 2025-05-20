package co.edu.uniquindio.proyecto.dto.planAlimenticio;

public record PlanAlimenticioDTO(

        Long codPlanAlimenticio,
        String nombre,
        Integer duracion,
        String descripcion,
        String objetivo,
        Long usuarioId

) {
}

package co.edu.uniquindio.proyecto.dto.nutricionista;

public record ClienteSuscritoDTO(
        String usuarioId,
        String primerNombre,
        String primerApellido,
        String planAlimenticio,
        String nombreSuscripcion,
        Double precio,
        Integer duracion
) {
}

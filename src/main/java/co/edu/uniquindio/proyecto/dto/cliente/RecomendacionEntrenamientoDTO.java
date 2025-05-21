package co.edu.uniquindio.proyecto.dto.cliente;

import java.sql.Date;

public record RecomendacionEntrenamientoDTO(
        String usuarioId,
        String nombreCompleto,
        Date fechaNacimiento,
        Long edad,
        String planesRecomendados
) {
}
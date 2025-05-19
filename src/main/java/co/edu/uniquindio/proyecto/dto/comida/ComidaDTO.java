package co.edu.uniquindio.proyecto.dto.comida;

import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;

import java.util.List;

public record ComidaDTO(
        Long codComida,
        String nombre,
        Integer porcion,
        Integer proteinas,
        Integer carbohidratos,
        Integer grasa,
        List<IngredienteDTO>ingredientes
) {
}

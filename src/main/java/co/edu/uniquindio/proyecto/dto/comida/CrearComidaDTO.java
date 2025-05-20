package co.edu.uniquindio.proyecto.dto.comida;

import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;

import java.util.List;

public record CrearComidaDTO(
         String nombre,
         Integer porcion,
         Integer proteinas,
         Integer carbohidratos,
         Integer grasa,
         Long codPlanAlimenticio

) {
}

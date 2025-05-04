package co.edu.uniquindio.proyecto.dto.nutricionista;

import java.util.List;

public record EditarNutricionistaDTO(

        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        Integer aniosExp,
        List<String> telefonos

) {
}

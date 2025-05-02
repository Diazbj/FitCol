package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.nutricionista.TituloDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.CrearNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.EditarNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.NutricionistaDTO;

public interface NutricionistaServicio {
    void crearNutricionista(CrearNutricionistaDTO crearNutricionistaDTO) throws Exception;
    NutricionistaDTO obtenerNutricionista(String id) throws Exception;
    void eliminarNutricionista(String id) throws Exception;
    void editarNutricionista(String id, EditarNutricionistaDTO editarNutricionistaDTO) throws Exception;
    void subirTitulo(TituloDTO tituloDTO, String id) throws Exception;
}

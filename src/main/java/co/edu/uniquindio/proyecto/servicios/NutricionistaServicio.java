package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.nutricionista.*;

import java.util.List;

public interface NutricionistaServicio {
    void crearNutricionista(CrearNutricionistaDTO crearNutricionistaDTO) throws Exception;
    NutricionistaDTO obtenerNutricionista() throws Exception;
    void eliminarNutricionista() throws Exception;
    void editarNutricionista( EditarNutricionistaDTO editarNutricionistaDTO) throws Exception;
    void subirTitulo(TituloDTO tituloDTO, String id) throws Exception;
    void editarTitulo(TituloDTO tituloDTO, String id) throws Exception;
    void eliminarTitulo(String id) throws Exception;
    TituloDTO obtenerTitulo(String id) throws Exception;
    List<TituloDTO> obtenerTitulos(String id) throws Exception;
    List<ClienteSuscritoDTO> obtenerClienteSuscrito() throws Exception;
}

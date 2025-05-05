package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ejercicio.CrearEjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioDTO;

import java.util.List;

public interface EjercicioServicio {

    void crearEjercicio(CrearEjercicioDTO dto) throws Exception;

    void editarEjercicio(Long id, CrearEjercicioDTO dto) throws Exception;

    EjercicioDTO obtenerEjercicio(Long id) throws Exception;

    List<EjercicioDTO> listarEjercicios();

    void eliminarEjercicio(Long id) throws Exception;
}

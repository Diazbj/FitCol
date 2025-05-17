package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ejercicio.CrearEjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioRutinaDTO;

import java.util.List;

public interface EjercicioServicio {

    void crearEjercicio(CrearEjercicioDTO dto) throws Exception;

    EjercicioRutinaDTO asignarEjercicioRutina(EjercicioRutinaDTO dto) throws Exception;

    void eliminarAsignacionEjercicioRutina(Long idEjercicio, Long idRutina) throws Exception;

    void editarEjercicio(Long id, CrearEjercicioDTO dto) throws Exception;

    EjercicioDTO obtenerEjercicio(Long id) throws Exception;

    List<EjercicioDTO> listarEjercicios();

    void eliminarEjercicio(Long id) throws Exception;
}

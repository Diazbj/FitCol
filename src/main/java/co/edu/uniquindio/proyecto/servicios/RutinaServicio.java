package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.AsignarRutinasDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.RutinaDTO;

import java.util.List;

public interface RutinaServicio {
    void crearRutina(CrearRutinaDTO dto) throws Exception;

    void editarRutina(Long id, CrearRutinaDTO dto) throws Exception;

    RutinaDTO obtenerRutina(Long id) throws Exception;

    List<RutinaDTO> listarRutinas(Long codigoEntrenador) throws Exception;

    void eliminarRutina(Long id) throws Exception;

    void asignarRutinasAPlan(AsignarRutinasDTO dto) throws Exception;
}

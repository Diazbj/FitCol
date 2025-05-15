package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.AsignarRutinasDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaCompletaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.RutinaCompletaDTO;

import java.util.List;

public interface RutinaServicio {
    void crearRutina(CrearRutinaCompletaDTO dto) throws Exception;

    void editarRutina(Long id, CrearRutinaDTO dto) throws Exception;

    CrearRutinaCompletaDTO obtenerRutina(Long id) throws Exception;

    List<RutinaCompletaDTO> listarRutinas() throws Exception;

    void eliminarRutina(Long id) throws Exception;

    void asignarRutinasAPlan(AsignarRutinasDTO dto) throws Exception;
}

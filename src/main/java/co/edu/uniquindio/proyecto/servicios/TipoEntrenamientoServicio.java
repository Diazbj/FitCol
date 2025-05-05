package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearTipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.TipoEntrenamientoDTO;

import java.util.List;

public interface TipoEntrenamientoServicio {
    void crearTipo(CrearTipoEntrenamientoDTO dto) throws Exception;
    void editarTipo(Long id, CrearTipoEntrenamientoDTO dto) throws Exception;
    TipoEntrenamientoDTO obtenerTipo(Long id) throws Exception;
    List<TipoEntrenamientoDTO> listarTipos();
    void eliminarTipo(Long id) throws Exception;
}


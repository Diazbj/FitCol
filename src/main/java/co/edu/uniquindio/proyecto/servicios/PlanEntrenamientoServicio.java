package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanEntrenamientoDTO;

import java.util.List;

public interface PlanEntrenamientoServicio {
    void crearPlanEntrenamiento(CrearPlanEntrenamientoDTO dto) throws Exception;
    void editarPlanEntrenamiento(Long id, CrearPlanEntrenamientoDTO dto) throws Exception;
    PlanEntrenamientoDTO obtenerPlanEntrenamiento(Long id) throws Exception;
    List<PlanEntrenamientoDTO> listarPlanesEntrenamiento() throws Exception;
    void eliminarPlanEntrenamiento(Long id) throws Exception;
}

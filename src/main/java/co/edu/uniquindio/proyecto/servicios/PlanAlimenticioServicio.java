package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.planAlimenticio.CrearPlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.dto.planAlimenticio.PlanAlimenticioDTO;

import java.util.List;

public interface PlanAlimenticioServicio {
    void crearPlanAlimenticio(CrearPlanAlimenticioDTO dto) throws Exception;
    void editarPlanAlimenticio(Long id, CrearPlanAlimenticioDTO dto) throws Exception;
    PlanAlimenticioDTO obtenerPlanAlimenticio(Long id) throws Exception;
    List<PlanAlimenticioDTO> listarPlanesAlimenticios();
    void eliminarPlanAlimenticio(Long id) throws Exception;
}
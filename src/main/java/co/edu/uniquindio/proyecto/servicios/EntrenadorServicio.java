package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.entrenador.*;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanDificultadDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanEntrenamientoDTO;

import java.util.List;

public interface EntrenadorServicio {

    void crearEntrenador(CrearEntrenadorDTO crearEntrenadorDTO) throws Exception;
    EntrenadorDTO obtenerEntrenador() throws Exception;
    void eliminarEntrenador() throws Exception;
    void editarEntrenador( EditarEntrenadorDTO editarEntrenadorDTO) throws Exception;
    void subirCertificado(CertificacionDTO certificacionDTO) throws Exception;
    List<EntrenadoresDestacadoDTO> obtenerEntrenadoresDestacados()throws Exception;
    List<CertificadoEntrenadorDTO> obtenerInformacionEntrenador()throws Exception;
    List<PlanDificultadDTO> listarPlanesPorDificultad() throws Exception;
}

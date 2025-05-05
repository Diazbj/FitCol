package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.entrenador.*;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;

public interface EntrenadorServicio {

    void crearPlanEntrenamiento(CrearPlanEntrenamientoDTO crearPlanEntrenamientoDTO, String id) throws Exception;
    void crearEntrenador(CrearEntrenadorDTO crearEntrenadorDTO) throws Exception;
    EntrenadorDTO obtenerEntrenador(String id) throws Exception;
    void eliminarEntrenador(String id) throws Exception;
    void editarEntrenador(String id, EditarEntrenadorDTO editarEntrenadorDTO) throws Exception;
    void subirCertificado(CertificacionDTO certificacionDTO,String id) throws Exception;
}

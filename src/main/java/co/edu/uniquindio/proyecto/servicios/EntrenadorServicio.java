package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.entrenador.*;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;

public interface EntrenadorServicio {

    void crearEntrenador(CrearEntrenadorDTO crearEntrenadorDTO) throws Exception;
    EntrenadorDTO obtenerEntrenador() throws Exception;
    void eliminarEntrenador() throws Exception;
    void editarEntrenador( EditarEntrenadorDTO editarEntrenadorDTO) throws Exception;
    void subirCertificado(CertificacionDTO certificacionDTO) throws Exception;
}

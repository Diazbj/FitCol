package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;

public interface EntrenadorServicio {

    void crearPlanEntrenamiento(CrearPlanEntrenamientoDTO crearPlanEntrenamientoDTO, String id) throws Exception;

}

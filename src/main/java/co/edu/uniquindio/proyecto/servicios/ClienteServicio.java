package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.cliente.*;

import java.util.List;

public interface ClienteServicio {
    void crearCliente(CrearClienteDTO crearClienteDTO) throws Exception;
    ClienteDTO obtenerCliente() throws Exception;
    void eliminarCliente() throws Exception;
    void editarCliente(EditarClienteDTO editarClienteDTO) throws Exception;
    RecomendacionEntrenamientoDTO obtenerRecomendacionEntrenamiento() throws Exception;
    List<ProgresoSemanalDTO> obtenerProgresoSemanal( ) throws Exception;
}

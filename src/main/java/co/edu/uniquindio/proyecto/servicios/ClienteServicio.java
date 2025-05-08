package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;

public interface ClienteServicio {
    void crearCliente(CrearClienteDTO crearClienteDTO) throws Exception;
    ClienteDTO obtenerCliente() throws Exception;
    void eliminarCliente() throws Exception;
    void editarCliente(EditarClienteDTO editarClienteDTO) throws Exception;
}

package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;

public interface ClienteServicio {
    void crearCliente(CrearClienteDTO crearClienteDTO) throws Exception;
    ClienteDTO obtenerCliente(String id) throws Exception;
    void eliminarCliente(String id) throws Exception;
    void editarCliente(String id, EditarClienteDTO editarClienteDTO) throws Exception;
}

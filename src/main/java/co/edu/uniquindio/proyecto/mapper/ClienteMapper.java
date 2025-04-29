package co.edu.uniquindio.proyecto.mapper;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ClienteMapper {

    ClienteDTO clienteToClienteDTO(Cliente cliente);

    Cliente clienteDTOToCliente(ClienteDTO clienteDTO);

    Cliente crearClienteDTOToCliente(CrearClienteDTO crearClienteDTO);

}

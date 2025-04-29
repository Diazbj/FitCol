package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.mapper.ClienteMapper;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.repositorio.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final ClienteMapper clienteMapper;

    @Override
    public void crearCliente(CrearClienteDTO crearClienteDTO) {

        Cliente cliente = clienteMapper.crearClienteDTOToCliente(crearClienteDTO);

        // Conversión manual para fecha si MapStruct no lo maneja automáticamente
        if (crearClienteDTO.fechaNacimiento() != null) {
            cliente.setFechaNacimiento(LocalDate.parse(crearClienteDTO.fechaNacimiento()));
        }

        clienteRepo.save(cliente);

    }

    @Override
    public ClienteDTO obtenerCliente(String id)throws Exception{

        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id: " + id));

        return clienteMapper.clienteToClienteDTO(cliente);
    }
}

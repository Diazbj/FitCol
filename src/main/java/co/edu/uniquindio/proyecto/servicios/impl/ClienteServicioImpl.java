package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.repositorio.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;

    @Override
    public void crearCliente(CrearClienteDTO crearClienteDTO) {

        Cliente cliente = new Cliente();
        cliente.setId(crearClienteDTO.id());
        cliente.setNombre(crearClienteDTO.nombre());
        cliente.setTelefono(crearClienteDTO.telefono());
        cliente.setPassword(crearClienteDTO.password());
        cliente.setPrimerApellido(crearClienteDTO.primerApellido());
        cliente.setSegundoApellido(crearClienteDTO.segundoApellido());
        cliente.setEmail(crearClienteDTO.email());
        cliente.setSexo(crearClienteDTO.sexo());
        cliente.setHistorialMedico(crearClienteDTO.historialMedico());
        cliente.setPeso(crearClienteDTO.peso() != null ? crearClienteDTO.peso().doubleValue() : null);
        cliente.setAltura(crearClienteDTO.altura() != null ? crearClienteDTO.altura().doubleValue() : null);

        // Conversión de fecha string a LocalDateTime
        cliente.setFechaNacimiento(LocalDateTime.parse(crearClienteDTO.fechaNacimiento() + "T00:00:00"));

        clienteRepo.save(cliente);

    }
}

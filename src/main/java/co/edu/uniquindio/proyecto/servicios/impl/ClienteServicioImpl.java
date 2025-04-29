package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;
import co.edu.uniquindio.proyecto.mapper.ClienteMapper;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.repositorio.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final ClienteMapper clienteMapper;

    @Override
    public void crearCliente(CrearClienteDTO crearClienteDTO) {
        // Validar si ya existe un cliente con ese ID
        if (clienteRepo.existsById(crearClienteDTO.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + crearClienteDTO.getId());
        }

        // Crear entidad Cliente desde el DTO
        Cliente cliente = new Cliente();
        cliente.setId(crearClienteDTO.getId());
        cliente.setNombre(crearClienteDTO.getNombre());
        cliente.setTelefono(crearClienteDTO.getTelefono());
        cliente.setPassword(crearClienteDTO.getPassword()); // Deberías encriptar esta contraseña
        cliente.setPrimerApellido(crearClienteDTO.getPrimerApellido());
        cliente.setSegundoApellido(crearClienteDTO.getSegundoApellido());
        cliente.setFechaNacimiento(LocalDate.parse(crearClienteDTO.getFechaNacimiento())); // Formato ISO
        cliente.setEmail(crearClienteDTO.getEmail());
        cliente.setSexo(crearClienteDTO.getSexo());
        cliente.setHistorialMedico(crearClienteDTO.getHistorialMedico());
        cliente.setPeso(crearClienteDTO.getPeso());
        cliente.setAltura(crearClienteDTO.getAltura());

        // Guardar cliente en la base de datos
        clienteRepo.save(cliente);
    }

    @Override
    public ClienteDTO obtenerCliente(String id)throws Exception{
        // Buscar el cliente por ID
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));

        // Calcular la edad
        int edad = calcularEdad(cliente.getFechaNacimiento());

        // Retornar el DTO con la edad calculada
        return new ClienteDTO(cliente, edad);
    }

    @Override
    public void eliminarCliente(String id)throws Exception{
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));
        clienteRepo.deleteById(id);
    }
    @Override
    public void editarCliente(String id, EditarClienteDTO editarClienteDTO)throws Exception{
        // Buscar el cliente por ID
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));

        // Actualizar los campos del cliente
        cliente.setNombre(editarClienteDTO.getNombre());
        cliente.setTelefono(editarClienteDTO.getTelefono());
        cliente.setPrimerApellido(editarClienteDTO.getPrimerApellido());
        cliente.setSegundoApellido(editarClienteDTO.getSegundoApellido());
        cliente.setSexo(editarClienteDTO.getSexo());
        cliente.setPeso(editarClienteDTO.getPeso() != null ? editarClienteDTO.getPeso().doubleValue() : null);
        cliente.setAltura(editarClienteDTO.getAltura() != null ? editarClienteDTO.getAltura().doubleValue() : null);

        // Guardar los cambios
        clienteRepo.save(cliente);
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }


}

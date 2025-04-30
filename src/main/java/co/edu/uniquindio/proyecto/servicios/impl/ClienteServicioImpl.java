package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;
import co.edu.uniquindio.proyecto.mapper.ClienteMapper;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final ClienteMapper clienteMapper;

    @Override
    public void crearCliente(CrearClienteDTO crearClienteDTO) {
        // Validar si ya existe un cliente con ese ID
        if (clienteRepo.existsById(crearClienteDTO.id())) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + crearClienteDTO.id());
        }

        Cliente cliente = clienteMapper.fromCrearDTOToEntity(crearClienteDTO);

        List<UsuarioTelefono> telefonos = crearClienteDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono tel = new UsuarioTelefono();
                    tel.setNumero(numero);
                    tel.setUsuario(cliente);
                    return tel;
                }).toList();

        cliente.setTelefonos(telefonos);

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
        return clienteMapper.fromEntityToDTO(cliente);
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


        // Guardar los cambios
        clienteRepo.save(cliente);
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }


}

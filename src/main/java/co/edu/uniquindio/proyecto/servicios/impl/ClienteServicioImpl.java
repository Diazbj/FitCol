package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.mapper.ClienteMapper;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorio.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final ClienteMapper clienteMapper;
    private final CiudadRepo ciudadRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void crearCliente(CrearClienteDTO crearClienteDTO) {
        // Validar si ya existe un cliente con ese ID
        if (clienteRepo.existsById(crearClienteDTO.usuarioId())) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + crearClienteDTO.usuarioId());
        }

        // Buscar la ciudad por ID
        Ciudad ciudad = ciudadRepo.findById(crearClienteDTO.codCiudad())
                .orElseThrow(() -> new IllegalArgumentException("La ciudad con ID " + crearClienteDTO.codCiudad() + " no existe"));

        Cliente cliente = clienteMapper.fromCrearDTOToEntity(crearClienteDTO);
        // Asignar la ciudad al cliente
        cliente.setCiudad(ciudad);
        cliente.setPassword(passwordEncoder.encode(crearClienteDTO.password()));


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
    public ClienteDTO obtenerCliente()throws Exception{

        String id = obtenerIdSesion();
        // Buscar el cliente por ID
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));

        // Calcular la edad
        int edad = calcularEdad(cliente.getFechaNacimiento());
        cliente.setUsuarioId(id);

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
    public void editarCliente( EditarClienteDTO editarClienteDTO)throws Exception{

        String id = obtenerIdSesion();
        // Buscar el cliente por ID
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));

        // Actualizar campos básicos con el mapper
        clienteMapper.actualizarClienteDesdeDTO(editarClienteDTO, cliente);

        // Mapear los teléfonos manualmente (MapStruct no puede manejar relaciones complejas fácilmente)
       cliente.getTelefonos().clear();
        List<UsuarioTelefono> nuevosTelefonos = editarClienteDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono telefono = new UsuarioTelefono();
                    telefono.setNumero(numero);
                    telefono.setUsuario(cliente); // Relación correcta
                    return telefono;
                })
                .toList();
        cliente.getTelefonos().addAll(nuevosTelefonos);

        // Guardar los cambios
        clienteRepo.save(cliente);
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    public String obtenerIdSesion(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }


}

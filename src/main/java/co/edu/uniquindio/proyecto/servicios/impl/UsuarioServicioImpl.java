package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.mapper.UsuarioMapper;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;

    @Override
    public void crearUsuario(CrearUsuarioDTO crearUsuarioDTO) {
        // Validar si ya existe un cliente con ese ID
        if (usuarioRepo.existsById(crearUsuarioDTO.id())) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + crearUsuarioDTO.id());
        }

        Usuario usuario = usuarioMapper.fromCrearDTOToEntity(crearUsuarioDTO);

        List<UsuarioTelefono> telefonos = crearUsuarioDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono tel = new UsuarioTelefono();
                    tel.setNumero(numero);
                    tel.setUsuario(usuario);
                    return tel;
                }).toList();

        usuario.setTelefonos(telefonos);

        usuarioRepo.save(usuario);

    }

    @Override
    public UsuarioDTO obtenerUsuario(String id)throws Exception{
        // Buscar el cliente por ID
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));

        // Calcular la edad
        int edad = calcularEdad(usuario.getFechaNacimiento());

        // Retornar el DTO con la edad calculada
        return usuarioMapper.fromEntityToDTO(usuario);
    }

    @Override
    public void eliminarUsuario(String id)throws Exception{
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new Exception("El cliente con ID " + id + " no existe"));
        usuarioRepo.deleteById(id);
    }
    @Override
    public void editarUsuario(String id, EditarUsuarioDTO editarUsuarioDTO)throws Exception{
        // Buscar el cliente por ID
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new Exception("El usuario con ID " + id + " no existe"));

        // Actualizar campos básicos con el mapper
        usuarioMapper.actualizarUsuarioDesdeDTO(editarUsuarioDTO, usuario);

        // Mapear los teléfonos manualmente (MapStruct no puede manejar relaciones complejas fácilmente)
       usuario.getTelefonos().clear();
        List<UsuarioTelefono> nuevosTelefonos = editarUsuarioDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono telefono = new UsuarioTelefono();
                    telefono.setNumero(numero);
                    telefono.setUsuario(usuario); // Relación correcta
                    return telefono;
                })
                .toList();
        usuario.getTelefonos().addAll(nuevosTelefonos);

        // Guardar los cambios
        usuarioRepo.save(usuario);
    }

    public int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }


}

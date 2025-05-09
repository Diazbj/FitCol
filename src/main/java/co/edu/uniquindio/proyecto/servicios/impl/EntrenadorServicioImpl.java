package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.entrenador.*;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.mapper.CertificadoMapper;
import co.edu.uniquindio.proyecto.mapper.EntrenadorMapper;
import co.edu.uniquindio.proyecto.mapper.PlanEntrenamientoMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.*;
import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.*;
import co.edu.uniquindio.proyecto.servicios.EntrenadorServicio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EntrenadorServicioImpl implements EntrenadorServicio {

    private final EntrenadorRepo entrenadorRepo;
    private final EntrenadorMapper entrenadorMapper;
    private final PlanEntrenamientoRepo planEntrenamientoRepo;
    private final PlanEntrenamientoMapper planEntrenamientoMapper;
    private final CiudadRepo ciudadRepo;
    private final CertificadoRepo certificadoRepo;
    private final CertificadoMapper certificadoMapper;
    private final EntrenadorCertificacionRepo entrenadorCertificacionRepo;
    private final PasswordEncoder passwordEncoder;
    private final ClienteServicioImpl clienteServicioImpl;


    @Override
    public void crearEntrenador(CrearEntrenadorDTO crearEntrenadorDTO)throws Exception{
        // Validar si ya existe un entrenador con ese ID
        if (entrenadorRepo.existsById(crearEntrenadorDTO.usuarioId())) {
            throw new IllegalArgumentException("Ya existe un entrenador con el ID: " + crearEntrenadorDTO.usuarioId());
        }

        Entrenador entrenador = entrenadorMapper.fromCrearDTOToEntity(crearEntrenadorDTO);


        List<UsuarioTelefono> telefonos = crearEntrenadorDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono tel = new UsuarioTelefono();
                    tel.setNumero(numero);
                    tel.setUsuario(entrenador);
                    return tel;
                }).toList();

        entrenador.setTelefonos(telefonos);
        entrenador.setPassword(passwordEncoder.encode(crearEntrenadorDTO.password()));

        entrenadorRepo.save(entrenador);
    }

    @Override
    public EntrenadorDTO obtenerEntrenador()throws Exception{

        String id = clienteServicioImpl.obtenerIdSesion();
        // Buscar el entrenador por ID
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));

        // Retornar el DTO con la edad calculada
        return entrenadorMapper.fromEntityToDTO(entrenador);
    }

    @Override
    public void eliminarEntrenador()throws Exception{

        String id = clienteServicioImpl.obtenerIdSesion();

        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));
        entrenadorRepo.deleteById(id);
    }
    @Override
    public void editarEntrenador(EditarEntrenadorDTO editarEntrenadorDTO)throws Exception{

        String id = clienteServicioImpl.obtenerIdSesion();

        // Buscar el entrenador por ID
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));

        // Actualizar campos básicos con el mapper
        entrenadorMapper.actualizarEntrenadorDesdeDTO(editarEntrenadorDTO, entrenador);

        // Mapear los teléfonos manualmente (MapStruct no puede manejar relaciones complejas fácilmente)
       entrenador.getTelefonos().clear();
        List<UsuarioTelefono> nuevosTelefonos = editarEntrenadorDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono telefono = new UsuarioTelefono();
                    telefono.setNumero(numero);
                    telefono.setUsuario(entrenador); // Relación correcta
                    return telefono;
                })
                .toList();
       entrenador.getTelefonos().addAll(nuevosTelefonos);


        // Guardar los cambios
        entrenadorRepo.save(entrenador);
    }


    @Override
    public void subirCertificado(CertificacionDTO certificacionDTO) throws Exception {

        String id= clienteServicioImpl.obtenerIdSesion();
        // 1. Buscar el entrenador por ID
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));

        // 2. Mapear y guardar la certificación
        Certificacion certificacion = certificadoMapper.fromCrearDTOtoEntity(certificacionDTO);
        certificacion = certificadoRepo.save(certificacion); // <--- asegurar que tenga el ID

        if (certificacion.getCodCertificacion() == null) {
            throw new Exception("Error: No se generó un ID de certificación.");
        }

        // 3. Crear ID compuesto y relación
        EntrenadorCertificacionId relacionId = new EntrenadorCertificacionId(
                certificacion.getCodCertificacion(),
                entrenador.getUsuarioId() // <-- verifica que este campo sea el mismo de tu PK
        );


        EntrenadorCertificacion relacion = new EntrenadorCertificacion(relacionId, entrenador, certificacion);

        // 4. Guardar la relación
        entrenadorCertificacionRepo.save(relacion);
    }


}

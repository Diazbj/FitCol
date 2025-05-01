package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.entrenador.EntrenadorDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.EditarEntrenadorDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.CrearEntrenadorDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.mapper.EntrenadorMapper;
import co.edu.uniquindio.proyecto.mapper.PlanEntrenamientoMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.EntrenadorRepo;
import co.edu.uniquindio.proyecto.repositorio.PlanEntrenamientoRepo;
import co.edu.uniquindio.proyecto.servicios.EntrenadorServicio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EntrenadorServicioImpl implements EntrenadorServicio {

    private final EntrenadorRepo entrenadorRepo;
    private final EntrenadorMapper entrenadorMapper;
    private final PlanEntrenamientoRepo planEntrenamientoRepo;
    private final PlanEntrenamientoMapper planEntrenamientoMapper;

    @Override
    public void crearPlanEntrenamiento(CrearPlanEntrenamientoDTO crearPlanEntrenamientoDTO, String id) {
        PlanEntrenamiento plan = planEntrenamientoMapper.dtoToEntidad(crearPlanEntrenamientoDTO);

        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrenador no encontrado"));

        plan.setEntrenador(entrenador);

        planEntrenamientoRepo.save(plan);
    }

    @Override
    public void crearEntrenador(CrearEntrenadorDTO crearEntrenadorDTO)throws Exception{
        // Validar si ya existe un entrenador con ese ID
        if (entrenadorRepo.existsById(crearEntrenadorDTO.id())) {
            throw new IllegalArgumentException("Ya existe un entrenador con el ID: " + crearEntrenadorDTO.id());
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

        entrenadorRepo.save(entrenador);
    }

    @Override
    public EntrenadorDTO obtenerEntrenador(String id)throws Exception{
        // Buscar el entrenador por ID
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));

        // Retornar el DTO con la edad calculada
        return entrenadorMapper.fromEntityToDTO(entrenador);
    }

    @Override
    public void eliminarEntrenador(String id)throws Exception{
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));
        entrenadorRepo.deleteById(id);
    }
    @Override
    public void editarEntrenador(String id, EditarEntrenadorDTO editarEntrenadorDTO)throws Exception{
        // Buscar el entrenador por ID
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("El entrenador con ID " + id + " no existe"));

        // Actualizar campos básicos con el mapper
        entrenadorMapper.actualizarEntrenadorDesdeDTO(editarEntrenadorDTO, entrenador);


        // Guardar los cambios
        entrenadorRepo.save(entrenador);
    }


}

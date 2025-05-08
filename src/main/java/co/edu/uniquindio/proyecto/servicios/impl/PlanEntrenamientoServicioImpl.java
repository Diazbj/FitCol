package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.mapper.PlanEntrenamientoMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import co.edu.uniquindio.proyecto.modelo.entrenador.TipoEntrenamiento;
import co.edu.uniquindio.proyecto.repositorio.EntrenadorRepo;
import co.edu.uniquindio.proyecto.repositorio.PlanEntrenamientoRepo;
import co.edu.uniquindio.proyecto.repositorio.TipoEntrenamientoRepo;
import co.edu.uniquindio.proyecto.servicios.PlanEntrenamientoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanEntrenamientoServicioImpl implements PlanEntrenamientoServicio {

    private final PlanEntrenamientoRepo planRepo;
    private final EntrenadorRepo entrenadorRepo;
    private final TipoEntrenamientoRepo tipoEntrenamientoRepo;
    private final PlanEntrenamientoMapper mapper;
    private final ClienteServicioImpl clienteServicioImpl;

    @Override
    public void crearPlanEntrenamiento(CrearPlanEntrenamientoDTO dto) throws Exception {
        PlanEntrenamiento plan = mapper.fromDTO(dto);
        String id=clienteServicioImpl.obtenerIdSesion();

        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new Exception("Entrenador no encontrado"));

        TipoEntrenamiento tipo = tipoEntrenamientoRepo.findById(dto.codTipoEntrenamiento())
                .orElseThrow(() -> new Exception("Tipo de entrenamiento no encontrado"));

        plan.setEntrenador(entrenador);
        plan.setTipoEntrenamiento(tipo);

        planRepo.save(plan);
    }

    @Override
    public void editarPlanEntrenamiento(Long id, CrearPlanEntrenamientoDTO dto) throws Exception {

        PlanEntrenamiento plan = planRepo.findById(id)
                .orElseThrow(() -> new Exception("Plan no encontrado"));

        plan.setNombre(dto.nombre());
        plan.setDuracion(dto.duracion());
        plan.setDificultad(dto.dificultad());
        plan.setDescripcion(dto.descripcion());

        String usuarioId=clienteServicioImpl.obtenerIdSesion();

        Entrenador entrenador = entrenadorRepo.findById(usuarioId)
                .orElseThrow(() -> new Exception("Entrenador no encontrado"));
        TipoEntrenamiento tipo = tipoEntrenamientoRepo.findById(dto.codTipoEntrenamiento())
                .orElseThrow(() -> new Exception("Tipo de entrenamiento no encontrado"));

        plan.setEntrenador(entrenador);
        plan.setTipoEntrenamiento(tipo);

        planRepo.save(plan);
    }

    @Override
    public PlanEntrenamientoDTO obtenerPlanEntrenamiento(Long id) throws Exception {
        PlanEntrenamiento plan = planRepo.findById(id)
                .orElseThrow(() -> new Exception("Plan no encontrado"));
        return mapper.toDTO(plan);
    }

    @Override
    public List<PlanEntrenamientoDTO> listarPlanesEntrenamiento() {
        return planRepo.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void eliminarPlanEntrenamiento(Long id) throws Exception {
        if (!planRepo.existsById(id)) {
            throw new Exception("No existe un plan con ID " + id);
        }
        planRepo.deleteById(id);
    }
}


package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.planAlimenticio.CrearPlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.dto.planAlimenticio.PlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.mapper.PlanAlimenticioMapper;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Nutricionista;
import co.edu.uniquindio.proyecto.modelo.nutricionista.PlanAlimenticio;
import co.edu.uniquindio.proyecto.repositorio.Consultas.NutricionistaRepo;
import co.edu.uniquindio.proyecto.repositorio.PlanAlimenticioRepo;

import co.edu.uniquindio.proyecto.servicios.PlanAlimenticioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanAlimenticioServicioImpl implements PlanAlimenticioServicio {

    private final PlanAlimenticioRepo planRepo;
    private final PlanAlimenticioMapper planAlimenticioMapper;
    private final ClienteServicioImpl clienteServicioImpl;
    private final NutricionistaRepo nutricionistaRepo;

    @Override
    public void crearPlanAlimenticio(CrearPlanAlimenticioDTO dto) throws Exception {
        String id = clienteServicioImpl.obtenerIdSesion();

        Nutricionista nutricionista= nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("Entrenador no encontrado"));

        PlanAlimenticio plan = planAlimenticioMapper.fromDTO(dto);
        plan.setNutricionista(nutricionista);

        planRepo.save(plan);
    }

    @Override
    public void editarPlanAlimenticio(Long id, CrearPlanAlimenticioDTO dto) throws Exception {
        PlanAlimenticio plan = planRepo.findById(id)
                .orElseThrow(() -> new Exception("Plan no encontrado"));

        plan.setNombre(dto.nombre());
        plan.setDuracion(dto.duracion());
        plan.setDescripcion(dto.descripcion());
        plan.setObjetivo(dto.objetivo());


        String usuarioId = clienteServicioImpl.obtenerIdSesion();

        Nutricionista nutricionista= nutricionistaRepo.findById(usuarioId)
                .orElseThrow(() -> new Exception("Entrenador no encontrado"));

        plan.setNutricionista(nutricionista);

        planRepo.save(plan);
    }

    @Override
    public PlanAlimenticioDTO obtenerPlanAlimenticio(Long id) throws Exception {
        PlanAlimenticio plan = planRepo.findById(id)
                .orElseThrow(() -> new Exception("Plan no encontrado"));


        return planAlimenticioMapper.toDTO(plan);
    }

    @Override
    public List<PlanAlimenticioDTO> listarPlanesAlimenticios() {

        String id= clienteServicioImpl.obtenerIdSesion();

        return planRepo.findByNutricionista_UsuarioId(id).stream()
                .map(planAlimenticioMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminarPlanAlimenticio(Long id) throws Exception {
        if (!planRepo.existsById(id)) {
            throw new Exception("No existe un plan con ID " + id);
        }
        planRepo.deleteById(id);
    }
}

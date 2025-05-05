package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.AsignarRutinasDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.RutinaDTO;
import co.edu.uniquindio.proyecto.mapper.RutinaMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import co.edu.uniquindio.proyecto.modelo.entrenador.RutinaPlanEnt;
import co.edu.uniquindio.proyecto.repositorio.PlanEntrenamientoRepo;
import co.edu.uniquindio.proyecto.repositorio.RutinaPlanEntRepo;
import co.edu.uniquindio.proyecto.repositorio.RutinaRepo;
import co.edu.uniquindio.proyecto.servicios.RutinaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutinaServicioImpl implements RutinaServicio {

    private final RutinaRepo rutinaRepo;
    private final RutinaMapper rutinaMapper;
    private final PlanEntrenamientoRepo planEntrenamientoRepo;
    private final RutinaPlanEntRepo rutinaPlanEntRepo;

    @Override
    public void crearRutina(CrearRutinaDTO dto) {
        Rutina rutina = rutinaMapper.fromCrearDTO(dto);
        rutinaRepo.save(rutina);
    }

    @Override
    public void editarRutina(Long id, CrearRutinaDTO dto) throws Exception {
        Rutina rutina = rutinaRepo.findById(id)
                .orElseThrow(() -> new Exception("Rutina con ID " + id + " no encontrada"));

        rutina.setNombre(dto.nombre());

        rutinaRepo.save(rutina);
    }

    @Override
    public RutinaDTO obtenerRutina(Long id) throws Exception {
        Rutina rutina = rutinaRepo.findById(id)
                .orElseThrow(() -> new Exception("Rutina con ID " + id + " no encontrada"));
        return rutinaMapper.toDTO(rutina);
    }

    @Override
    public List<RutinaDTO> listarRutinas(Long codigoEntrenador) {
        return rutinaRepo.obtenerRutinasPorEntrenador(codigoEntrenador).stream()
                .map(rutinaMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminarRutina(Long id) throws Exception {
        if (!rutinaRepo.existsById(id)) {
            throw new Exception("No existe una rutina con ID " + id);
        }
        rutinaRepo.deleteById(id);
    }

    @Override
    public void asignarRutinasAPlan(AsignarRutinasDTO dto) {
        PlanEntrenamiento plan = planEntrenamientoRepo.findById(dto.codPlanEntrenamiento())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        for (Long codRutina : dto.codigosRutinas()) {
            Rutina rutina = rutinaRepo.findById(codRutina)
                    .orElseThrow(() -> new RuntimeException("Rutina no encontrada"));

            RutinaPlanEnt relacion = new RutinaPlanEnt();
            relacion.setRutina(rutina);
            relacion.setPlanEntrenamiento(plan);

            rutinaPlanEntRepo.save(relacion);
        }
    }

}

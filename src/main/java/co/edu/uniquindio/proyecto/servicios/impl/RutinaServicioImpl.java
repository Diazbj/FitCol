package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioRutinaDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.AsignarRutinasDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaCompletaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.mapper.RutinaMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.*;
import co.edu.uniquindio.proyecto.repositorio.*;
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
    private final EjercicioRutinaRepo ejercicioRutinaRepo;
    private final EjercicioRepo ejercicioRepo;

    @Override
    public void crearRutina(CrearRutinaCompletaDTO dto) {
        // Obtener el plan utilizando el mapper
        PlanEntrenamiento plan = planEntrenamientoRepo.findById(dto.codPlanEntrenamiento())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        // Crear y guardar la rutina usando el RutinaMapper
        Rutina rutina = rutinaMapper.fromCrearDTO(dto);
        rutinaRepo.save(rutina);

        // Asociar rutina con el plan
        RutinaPlanEnt relacion = new RutinaPlanEnt();
        relacion.setPlanEntrenamiento(plan);
        relacion.setRutina(rutina);
        rutinaPlanEntRepo.save(relacion);

        // Asociar ejercicios con la rutina usando EjercicioRutinaMapper
        for (EjercicioRutinaDTO ej : dto.ejercicios()) {
            Ejercicio ejercicio = ejercicioRepo.findById(ej.idEjercicio())
                    .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

            EjercicioRutina relacionEj = new EjercicioRutina();
            relacionEj.setRutina(rutina);
            relacionEj.setEjercicio(ejercicio);
            relacionEj.setNumeroSeries(ej.numeroSeries());
            relacionEj.setNumeroRepeticiones(ej.numeroRepeticiones());

            ejercicioRutinaRepo.save(relacionEj);
        }
    }


    @Override
    public void editarRutina(Long id, CrearRutinaDTO dto) throws Exception {
        Rutina rutina = rutinaRepo.findById(id)
                .orElseThrow(() -> new Exception("Rutina con ID " + id + " no encontrada"));

        rutina.setNombre(dto.nombre());

        rutinaRepo.save(rutina);
    }

    @Override
    public CrearRutinaCompletaDTO obtenerRutina(Long id) throws Exception {
        Rutina rutina = rutinaRepo.findById(id)
                .orElseThrow(() -> new Exception("Rutina con ID " + id + " no encontrada"));
        return rutinaMapper.toDTO(rutina);
    }

    @Override
    public List<CrearRutinaCompletaDTO> listarRutinas(Long codigoEntrenador) {
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

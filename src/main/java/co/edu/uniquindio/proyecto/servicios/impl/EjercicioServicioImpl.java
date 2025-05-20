package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ejercicio.CrearEjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioRutinaDTO;
import co.edu.uniquindio.proyecto.mapper.EjercicioMapper;
import co.edu.uniquindio.proyecto.mapper.EjercicioRutinaMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.Ejercicio;
import co.edu.uniquindio.proyecto.modelo.entrenador.EjercicioRutina;
import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import co.edu.uniquindio.proyecto.repositorio.EjercicioRepo;
import co.edu.uniquindio.proyecto.repositorio.EjercicioRutinaRepo;
import co.edu.uniquindio.proyecto.repositorio.RutinaRepo;
import co.edu.uniquindio.proyecto.servicios.EjercicioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EjercicioServicioImpl implements EjercicioServicio {

    private final RutinaRepo rutinaRepo;
    private final EjercicioRutinaRepo ejercicioRutinaRepo;

    private final EjercicioRepo ejercicioRepo;
    private final EjercicioMapper ejercicioMapper;
    private final ClienteServicioImpl clienteServicioImpl;


    @Autowired
    private EjercicioRutinaMapper ejercicioRutinaMapper;

    @Override
    public void crearEjercicio(CrearEjercicioDTO dto) throws Exception {
        Ejercicio ejercicio = ejercicioMapper.fromCrearDTO(dto);
        ejercicioRepo.save(ejercicio);
    }

    @Override
    public EjercicioRutinaDTO asignarEjercicioRutina(EjercicioRutinaDTO dto) throws Exception {
        // Buscar el ejercicio
        Ejercicio ejercicio = ejercicioRepo.findById(dto.codEjercicio())
                .orElseThrow(() -> new Exception("No se encontró el ejercicio con ID " + dto.codEjercicio()));

        // Buscar la rutina
        Rutina rutina = rutinaRepo.findById(dto.codRutina())
                .orElseThrow(() -> new Exception("No se encontró la rutina con ID " + dto.codRutina()));

        // Crear la entidad EjercicioRutina
        EjercicioRutina ejercicioRutina = new EjercicioRutina();
        ejercicioRutina.setEjercicio(ejercicio);
        ejercicioRutina.setRutina(rutina);
        ejercicioRutina.setNumeroSeries(dto.numeroSeries());
        ejercicioRutina.setNumeroRepeticiones(dto.numeroRepeticiones());

        // Guardar
        ejercicioRutinaRepo.save(ejercicioRutina);

        // Convertir a DTO con el nombre del ejercicio
        return ejercicioRutinaMapper.toDTO(ejercicioRutina);
    }

    @Override
    public void eliminarAsignacionEjercicioRutina(Long idEjercicio, Long idRutina) throws Exception {
        Ejercicio ejercicio = ejercicioRepo.findById(idEjercicio)
                .orElseThrow(() -> new Exception("No se encontró el ejercicio con ID " + idEjercicio));

        Rutina rutina = rutinaRepo.findById(idRutina)
                .orElseThrow(() -> new Exception("No se encontró la rutina con ID " + idRutina));

        // Buscar la asignación entre ejercicio y rutina
        EjercicioRutina ejercicioRutina = ejercicioRutinaRepo.findByEjercicioAndRutina(ejercicio, rutina)
                .orElseThrow(() -> new Exception("No se encontró la asignación entre el ejercicio y la rutina"));

        ejercicioRutinaRepo.delete(ejercicioRutina);
    }



    @Override
    public void editarEjercicio(Long id, CrearEjercicioDTO dto) throws Exception {
        Ejercicio ejercicio = ejercicioRepo.findById(id)
                .orElseThrow(() -> new Exception("No se encontró el ejercicio con ID " + id));

        ejercicio.setNombre(dto.nombre());
        ejercicio.setDescripcion(dto.descripcion());

        ejercicioRepo.save(ejercicio);
    }

    @Override
    public EjercicioDTO obtenerEjercicio(Long id) throws Exception {
        Ejercicio ejercicio = ejercicioRepo.findById(id)
                .orElseThrow(() -> new Exception("No se encontró el ejercicio con ID " + id));
        return ejercicioMapper.toDTO(ejercicio);
    }

    @Override
    public List<EjercicioDTO> listarEjercicios() {
        return ejercicioRepo.findAll()
                .stream()
                .map(ejercicioMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminarEjercicio(Long id) throws Exception {
        if (!ejercicioRepo.existsById(id)) {
            throw new Exception("No existe un ejercicio con ID " + id);
        }
        ejercicioRepo.deleteById(id);
    }


}
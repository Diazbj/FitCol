package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ejercicio.CrearEjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioDTO;
import co.edu.uniquindio.proyecto.mapper.EjercicioMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.Ejercicio;
import co.edu.uniquindio.proyecto.repositorio.EjercicioRepo;
import co.edu.uniquindio.proyecto.servicios.EjercicioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EjercicioServicioImpl implements EjercicioServicio {

    private final EjercicioRepo ejercicioRepo;
    private final EjercicioMapper ejercicioMapper;

    @Override
    public void crearEjercicio(CrearEjercicioDTO dto) throws Exception {
        Ejercicio ejercicio = ejercicioMapper.fromCrearDTO(dto);
        ejercicioRepo.save(ejercicio);
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
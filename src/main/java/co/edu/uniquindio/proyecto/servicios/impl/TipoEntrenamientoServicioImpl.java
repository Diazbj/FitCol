package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearTipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.TipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.mapper.TipoEntrenamientoMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.TipoEntrenamiento;
import co.edu.uniquindio.proyecto.repositorio.TipoEntrenamientoRepo;
import co.edu.uniquindio.proyecto.servicios.TipoEntrenamientoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEntrenamientoServicioImpl implements TipoEntrenamientoServicio {

    private final TipoEntrenamientoRepo repo;
    private final TipoEntrenamientoMapper mapper;

    @Override
    public void crearTipo(CrearTipoEntrenamientoDTO dto) throws Exception {
        if (repo.existsByNombre(dto.nombre())) {
            throw new Exception("Ya existe un tipo de entrenamiento con ese nombre");
        }
        TipoEntrenamiento tipo = mapper.fromCrearDTO(dto);
        repo.save(tipo);
    }

    @Override
    public void editarTipo(Long id, CrearTipoEntrenamientoDTO dto) throws Exception {
        TipoEntrenamiento tipo = repo.findById(id)
                .orElseThrow(() -> new Exception("Tipo no encontrado"));

        tipo.setNombre(dto.nombre());
        repo.save(tipo);
    }

    @Override
    public TipoEntrenamientoDTO obtenerTipo(Long id) throws Exception {
        TipoEntrenamiento tipo = repo.findById(id)
                .orElseThrow(() -> new Exception("Tipo no encontrado"));
        return mapper.toDTO(tipo);
    }

    @Override
    public List<TipoEntrenamientoDTO> listarTipos() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void eliminarTipo(Long id) throws Exception {
        if (!repo.existsById(id)) {
            throw new Exception("No existe un tipo con ID " + id);
        }
        repo.deleteById(id);
    }
}

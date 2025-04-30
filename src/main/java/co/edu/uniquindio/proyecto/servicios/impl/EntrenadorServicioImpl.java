package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.mapper.EntrenadorMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import co.edu.uniquindio.proyecto.repositorio.EntrenadorRepo;
import co.edu.uniquindio.proyecto.repositorio.PlanEntrenamientoRepo;
import co.edu.uniquindio.proyecto.servicios.EntrenadorServicio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EntrenadorServicioImpl implements EntrenadorServicio {

    private final EntrenadorRepo entrenadorRepo;
    private final EntrenadorMapper entrenadorMapper;
    private final PlanEntrenamientoRepo planEntrenamientoRepo;

    @Override
    public void crearPlanEntrenamiento(CrearPlanEntrenamientoDTO entrenamientoDTO, String id) {
        PlanEntrenamiento plan = entrenadorMapper.dtoToEntidad(entrenamientoDTO);

        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrenador no encontrado"));

        plan.setEntrenador(entrenador);

        planEntrenamientoRepo.save(plan);
    }

}

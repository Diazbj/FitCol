package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import co.edu.uniquindio.proyecto.modelo.nutricionista.PlanAlimenticio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanAlimenticioRepo extends JpaRepository<PlanAlimenticio, Long> {

    List<PlanAlimenticio> findByNutricionista_UsuarioId(String id);

}

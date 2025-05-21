package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanEntrenamientoRepo extends JpaRepository<PlanEntrenamiento, Long> {

    List<PlanEntrenamiento> findByEntrenador_UsuarioId(String id);
}

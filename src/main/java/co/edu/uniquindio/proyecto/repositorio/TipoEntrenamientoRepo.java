package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.entrenador.TipoEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEntrenamientoRepo extends JpaRepository<TipoEntrenamiento, Long> {
        boolean existsByNombre(String nombre);
}

package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.nutricionista.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepo extends JpaRepository<Comida, Long> {
}

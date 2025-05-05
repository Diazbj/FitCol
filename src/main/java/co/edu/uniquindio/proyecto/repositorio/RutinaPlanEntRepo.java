package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.entrenador.RutinaPlanEnt;
import co.edu.uniquindio.proyecto.modelo.entrenador.RutinaPlanEntId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutinaPlanEntRepo extends JpaRepository<RutinaPlanEnt, RutinaPlanEntId> {
}

package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.entrenador.EjercicioRutina;
import co.edu.uniquindio.proyecto.modelo.entrenador.EjercicioRutinaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRutinaRepo extends JpaRepository<EjercicioRutina, EjercicioRutinaId> {
}

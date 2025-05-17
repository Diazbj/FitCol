package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.entrenador.Ejercicio;
import co.edu.uniquindio.proyecto.modelo.entrenador.EjercicioRutina;
import co.edu.uniquindio.proyecto.modelo.entrenador.EjercicioRutinaId;
import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EjercicioRutinaRepo extends JpaRepository<EjercicioRutina, EjercicioRutinaId> {

    Optional<EjercicioRutina> findByEjercicioAndRutina(Ejercicio ejercicio, Rutina rutina);
}

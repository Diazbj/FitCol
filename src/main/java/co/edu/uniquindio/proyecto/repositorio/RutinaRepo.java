package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.entrenador.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepo extends JpaRepository<Rutina, Long> {

    @Query("SELECT DISTINCT r FROM Rutina r JOIN r.rutinaPlanEnts rpe JOIN rpe.planEntrenamiento pe WHERE pe.entrenador.usuarioId = :codigoEntrenador")
    List<Rutina> obtenerRutinasPorEntrenador(@Param("codigoEntrenador") Long codigoEntrenador);


}

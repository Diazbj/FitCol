package co.edu.uniquindio.proyecto.repositorio.Consultas;

import co.edu.uniquindio.proyecto.dto.nutricionista.ClienteSuscritoDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutricionistaRepo extends JpaRepository<Nutricionista, String> {

    /*------------------------------------Consulta Intermedia 3 ----------------------------------------------------------------------------------*/
    @Query("""
    SELECT new co.edu.uniquindio.proyecto.dto.nutricionista.ClienteSuscritoDTO(
        u.usuarioId,
        u.primerNombre,
        u.primerApellido,
        pa.nombre,
        s.nombre,
        s.precio,
        s.duracion
    )
    FROM Suscripcion s
    JOIN s.planAlimenticio pa
    JOIN pa.nutricionista n
    JOIN s.cliente u
    WHERE n.usuarioId = :usuarioId
""")
    List<ClienteSuscritoDTO> findClientesPorNutricionista(@Param("usuarioId") String usuarioId);

    /*------------------------------------Consulta Intermedia 3 ----------------------------------------------------------------------------------*/

}

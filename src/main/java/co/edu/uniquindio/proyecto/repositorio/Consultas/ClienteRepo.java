package co.edu.uniquindio.proyecto.repositorio.Consultas;

import co.edu.uniquindio.proyecto.dto.cliente.ProgresoSemanalDTO;
import co.edu.uniquindio.proyecto.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniquindio.proyecto.dto.cliente.RecomendacionEntrenamientoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    /*------------------------------------Consulta Avanzada 1 ----------------------------------------------------------------------------------*/

    @Query(value = """

            SELECT\s
                u.usuario_id AS usuarioId,
                CONCAT(u.primer_nombre, ' ', u.primer_apellido) AS nombreCompleto,
                c.fecha_nacimiento AS fechaNacimiento,
                TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) AS edad,
                (
                    SELECT GROUP_CONCAT(
                        CONCAT(pe.nombre, ' (', eu.primer_nombre, ' ', eu.primer_apellido, ')') SEPARATOR ', '
                    )
                    FROM plan_entrenamiento pe
                    JOIN entrenador en ON pe.usuario_id = en.usuario_id
                    JOIN usuario eu ON en.usuario_id = eu.usuario_id
                    WHERE\s
                        (
                            (TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) < 26 AND pe.dificultad = 'Dificil') OR
                            (TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) BETWEEN 27 AND 40 AND pe.dificultad = 'Intermedio') OR
                            (TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) > 40 AND pe.dificultad = 'Facil')
                        )
                ) AS planesRecomendados
            FROM usuario u
            JOIN cliente c ON u.usuario_id = c.usuario_id
            WHERE u.usuario_id = :usuarioId
        """, nativeQuery = true)
    RecomendacionEntrenamientoDTO obtenerRecomendacionPorEdad(@Param("usuarioId") String usuarioId);
    /*------------------------------------Consulta Avanzada 1 ----------------------------------------------------------------------------------*/

    /*------------------------------------Consulta Intermedia 1 ----------------------------------------------------------------------------------*/

    @Query(value = """
    SELECT 
        STR_TO_DATE(CONCAT(YEAR(fecha_registro), ' ', WEEK(fecha_registro, 1), ' Monday'), '%X %V %W') AS semana,
        ROUND(AVG(peso), 2) AS pesoSemana,
        ROUND(AVG(indicemc), 2) AS imcSemana,
        ROUND(AVG(ent_completos), 2) AS entCompletosSemana
    FROM progreso
    WHERE usuario_id = :usuarioId
    GROUP BY semana
    ORDER BY semana
""", nativeQuery = true)
    List<ProgresoSemanalDTO> obtenerProgresoSemanal(@Param("usuarioId") String usuarioId);

    /*------------------------------------Consulta Intermedia 1 ----------------------------------------------------------------------------------*/

}

package co.edu.uniquindio.proyecto.repositorio.Consultas;

import co.edu.uniquindio.proyecto.dto.entrenador.CertificadoEntrenadorDTO;
import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenadorRepo extends JpaRepository<Entrenador, String> {

    /*------------------------------------Consulta Avanzada 2 ----------------------------------------------------------------------------------*/

    @Query(value = """
        WITH usuarios_activos AS (
            SELECT 
                pr.usuario_id AS cliente_id,
                pe.usuario_id AS entrenador_id,
                SUM(pr.ent_completos) AS total_entrenamientos
            FROM progreso pr
            JOIN suscripcion s ON pr.usuario_id = s.usuario_id
            JOIN plan_entrenamiento pe ON s.cod_plan_entrenamiento = pe.cod_plan_entrenamiento
            WHERE pr.fecha_registro >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
            GROUP BY pr.usuario_id, pe.usuario_id
            HAVING total_entrenamientos >= 12
        ),
        planes_filtrados AS (
            SELECT 
                pe.cod_plan_entrenamiento AS plan_id,
                pe.usuario_id AS entrenador_id
            FROM plan_entrenamiento pe
            JOIN suscripcion s ON s.cod_plan_entrenamiento = pe.cod_plan_entrenamiento
            WHERE s.precio > 180000
        )
        SELECT 
            u.usuario_id AS id,
            u.primer_nombre AS nombre,
            u.primer_apellido AS apellido,
            COUNT(DISTINCT pf.plan_id) AS cantidadPlanes
        FROM usuario u
        JOIN planes_filtrados pf ON pf.entrenador_id = u.usuario_id
        JOIN usuarios_activos ua ON ua.entrenador_id = u.usuario_id
        WHERE u.tipo_usuario = 'entrenador'
        GROUP BY u.usuario_id, u.primer_nombre, u.primer_apellido
        ORDER BY cantidadPlanes DESC
        LIMIT 3
    """, nativeQuery = true)
    List<Object[]> obtenerEntrenadoresDestacados();

    /*------------------------------------Consulta Avanzada 2 ----------------------------------------------------------------------------------*/

    /*------------------------------------Consulta Intermedia 2 ----------------------------------------------------------------------------------*/

    @Query(value = """
    SELECT 
        e.usuario_id AS idEntrenador,
        e.anios_exp AS aniosExperiencia,
        c.nombre AS nombreCertificado,
        c.institucion AS institucionCertificado
    FROM entrenador e
    JOIN entrenador_certificacion ec ON e.usuario_id = ec.usuario_id
    JOIN certificacion c ON ec.cod_certificacion = c.cod_certificacion
    WHERE e.usuario_id = :usuarioId
""", nativeQuery = true)
    List<CertificadoEntrenadorDTO> obtenerInfoEntrenadorPorId(@Param("usuarioId") String usuarioId);


    /*------------------------------------Consulta Intermedia 2 ----------------------------------------------------------------------------------*/

}

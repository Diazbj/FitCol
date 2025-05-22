package co.edu.uniquindio.proyecto.repositorio.Consultas;

import co.edu.uniquindio.proyecto.dto.progreso.RankingClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoRepo extends JpaRepository<Progreso, Long> {

    /*------------------------------------Consulta Intermedia 4 ok ---------------------------------------------------------------------------------8-*/

    @Query(value = """
    SELECT 
        u.primer_nombre,
        u.segundo_apellido,
        p.usuario_id AS cliente_id,
        SUM(p.ent_completos) AS total
    FROM 
        Progreso p
    JOIN 
        Cliente c ON p.usuario_id = c.usuario_id
    JOIN 
        Usuario u ON c.usuario_id = u.usuario_id
    GROUP BY 
        p.usuario_id
    HAVING 
        total >= 5
    ORDER BY 
        total DESC
    LIMIT 5
""", nativeQuery = true)
    List<Object[]> obtenerTop5ClientesActivos();

    /*------------------------------------Consulta Intermedia 4 ---------------------------------------------------------------------------------8-*/

    /*------------------------------------Consulta Avanzada 3 ok ---------------------------------------------------------------------------------9-*/

    @Query(value = """
    SELECT 
        pa.cod_plan_alimenticio,
        pa.nombre AS nombre_plan,
        pa.duracion,
        pa.objetivo,
        pa.usuario_id,
        CONCAT(u.primer_nombre, ' ', u.segundo_nombre, ' ', u.primer_apellido, ' ', u.segundo_apellido) AS nombre_nutricionista,
        n.anios_exp,
        (
            SELECT SUM(cpa.calorias_diarias)
            FROM comida_plan_ali cpa
            WHERE cpa.cod_plan_alimenticio = pa.cod_plan_alimenticio
        ) AS calorias_totales_plan
    FROM 
        plan_alimenticio pa
    JOIN 
        nutricionista n ON pa.usuario_id = n.usuario_id
    JOIN 
        usuario u ON pa.usuario_id = u.usuario_id
    WHERE 
        n.anios_exp >= 5
        AND (
            SELECT SUM(cpa.calorias_diarias)
            FROM comida_plan_ali cpa
            WHERE cpa.cod_plan_alimenticio = pa.cod_plan_alimenticio
        ) < (
            SELECT AVG(total_calorias)
            FROM (
                SELECT cod_plan_alimenticio, SUM(calorias_diarias) AS total_calorias
                FROM comida_plan_ali
                GROUP BY cod_plan_alimenticio
            ) AS subquery_avg
        )
""", nativeQuery = true)
    List<Object[]> obtenerPlanesDeficitNutricionistas();


    /*------------------------------------Consulta Avanzada 3 ---------------------------------------------------------------------------------9-*/

}

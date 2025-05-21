package co.edu.uniquindio.proyecto.repositorio.Consultas;

import co.edu.uniquindio.proyecto.dto.progreso.RankingClienteDTO;
import co.edu.uniquindio.proyecto.modelo.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoRepo extends JpaRepository<Progreso, Long> {

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



}

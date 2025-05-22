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
    u.usuario_id AS entrenador_id,
    u.primer_nombre,
    u.primer_apellido,
    COUNT(DISTINCT pf.plan_id) AS planes_validos
FROM usuario u
JOIN planes_filtrados pf ON pf.entrenador_id = u.usuario_id
JOIN usuarios_activos ua ON ua.entrenador_id = u.usuario_id
WHERE u.tipo_usuario = 'entrenador'
GROUP BY u.usuario_id, u.primer_nombre, u.primer_apellido
ORDER BY planes_validos DESC
LIMIT 3;
	SELECT 
        pr.usuario_id AS cliente_id,
        pe.usuario_id AS entrenador_id,
        SUM(pr.ent_completos) AS total_entrenamientos
    FROM progreso pr
    JOIN suscripcion s ON pr.usuario_id = s.usuario_id
    JOIN plan_entrenamiento pe ON s.cod_plan_entrenamiento = pe.cod_plan_entrenamiento
    WHERE pr.fecha_registro >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)
    GROUP BY pr.usuario_id, pe.usuario_id

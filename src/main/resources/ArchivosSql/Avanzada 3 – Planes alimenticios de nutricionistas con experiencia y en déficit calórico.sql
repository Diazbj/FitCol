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
    );

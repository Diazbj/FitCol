SELECT p.usuario_id, u.primer_nombre, u.primer_apellido
FROM (
    SELECT usuario_id,
           peso,
           LAG(peso, 1) OVER (PARTITION BY usuario_id ORDER BY fecha_registro) AS peso_prev1,
           LAG(peso, 2) OVER (PARTITION BY usuario_id ORDER BY fecha_registro) AS peso_prev2
    FROM progreso
) p
JOIN usuario u ON u.usuario_id = p.usuario_id
WHERE p.peso < p.peso_prev1 AND p.peso_prev1 < p.peso_prev2
GROUP BY p.usuario_id, u.primer_nombre, u.primer_apellido;

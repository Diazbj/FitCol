SELECT p.cliente_usuario_codigo, u.primer_nombre, u.primer_apellido
FROM (
    SELECT cliente_usuario_codigo,
           peso,
           LAG(peso, 1) OVER (PARTITION BY cliente_usuario_codigo ORDER BY fecha_registro) AS peso_prev1,
           LAG(peso, 2) OVER (PARTITION BY cliente_usuario_codigo ORDER BY fecha_registro) AS peso_prev2
    FROM progreso
) p
JOIN usuario u ON u.usuario_id = p.cliente_usuario_codigo
WHERE p.peso < p.peso_prev1 AND p.peso_prev1 < p.peso_prev2
GROUP BY p.cliente_usuario_codigo, u.primer_nombre, u.primer_apellido;

SELECT 
    u.primer_nombre,
    u.segundo_apellido,
    p.usuario_id AS cliente_id,
    SUM(p.ent_completos) AS total
FROM 
    progreso p
JOIN 
    cliente c ON p.usuario_id = c.usuario_id
JOIN 
    usuario u ON c.usuario_id = u.usuario_id
GROUP BY 
    p.usuario_id
HAVING 
    total >= 5
ORDER BY 
    total DESC
LIMIT 5;

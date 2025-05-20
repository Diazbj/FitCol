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
LIMIT 5;

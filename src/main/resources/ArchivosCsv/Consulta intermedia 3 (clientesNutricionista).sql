SELECT 
    u.usuario_id,
    u.primer_nombre,
    u.primer_apellido,
    pa.nombre AS plan_alimenticio,
    s.nombre AS nombre_suscripcion,
    s.precio,
    s.duracion
FROM suscripcion s
JOIN plan_alimenticio pa ON s.cod_plan_alimenticio = pa.cod_plan_alimenticio
JOIN nutricionista n ON pa.usuario_id = n.usuario_id
JOIN usuario u ON s.usuario_id = u.usuario_id
WHERE n.usuario_id = 1094886820;

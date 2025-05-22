SELECT 
    u.usuario_id AS usuarioId,
    u.primer_nombre AS primerNombre,
    u.primer_apellido AS primerApellido,
    pa.nombre AS nombrePlanAlimenticio,
    s.nombre AS nombreSuscripcion,
    s.precio AS precio,
    s.duracion AS duracion
FROM suscripcion s
JOIN plan_alimenticio pa ON s.cod_plan_alimenticio = pa.cod_plan_alimenticio
JOIN nutricionista n ON pa.usuario_id = n.usuario_id
JOIN usuario u ON s.usuario_id = u.usuario_id
WHERE n.usuario_id = '1094886820';

SELECT 
    e.usuario_id AS id_entrenador,
    e.anios_exp AS anios_experiencia,
    c.nombre AS nombre_certificado,
    c.institucion AS institucion_certificado
FROM entrenador e
JOIN entrenador_certificacion ec ON e.usuario_id = ec.usuario_id
JOIN certificacion c ON ec.cod_certificacion = c.cod_certificacion
WHERE e.usuario_id = 1094935673;

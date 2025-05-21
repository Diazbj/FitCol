SELECT 
    u.usuario_id AS usuarioId,
    CONCAT(u.primer_nombre, ' ', u.primer_apellido) AS nombreCompleto,
    c.fecha_nacimiento AS fechaNacimiento,
    TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) AS edad,
    (
        SELECT GROUP_CONCAT(
            CONCAT(pe.nombre, ' (', eu.primer_nombre, ' ', eu.primer_apellido, ')') SEPARATOR ', '
        )
        FROM plan_entrenamiento pe
        JOIN entrenador en ON pe.usuario_id = en.usuario_id
        JOIN usuario eu ON en.usuario_id = eu.usuario_id
        WHERE 
            (
                (TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) < 26 AND pe.dificultad = 'Dificil') OR
                (TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) BETWEEN 27 AND 40 AND pe.dificultad = 'Intermedio') OR
                (TIMESTAMPDIFF(YEAR, c.fecha_nacimiento, CURDATE()) > 40 AND pe.dificultad = 'Facil')
            )
    ) AS planesRecomendados
FROM usuario u
JOIN cliente c ON u.usuario_id = c.usuario_id
WHERE u.usuario_id = '1098337495';  -- <--- Reemplaza este ID por el que necesites

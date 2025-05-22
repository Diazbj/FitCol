SELECT 
    STR_TO_DATE(CONCAT(YEAR(fecha_registro), ' ', WEEK(fecha_registro, 1), ' Monday'), '%X %V %W') AS semana,
    ROUND(AVG(peso), 2) AS pesoSemana,
    ROUND(AVG(indicemc), 2) AS imcSemana,
    ROUND(AVG(ent_completos), 2) AS entCompletosSemana
FROM progreso
WHERE usuario_id = 1098337495
GROUP BY semana
ORDER BY semana;
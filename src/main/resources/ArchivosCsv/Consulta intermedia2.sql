SELECT 
    STR_TO_DATE(CONCAT(YEAR(fecha_registro), ' ', WEEK(fecha_registro, 1), ' Monday'), '%X %V %W') AS semana,
    ROUND(AVG(peso), 2) AS peso_semana,
    ROUND(AVG(indicemc), 2) AS imc_semana,
    ROUND(AVG(ent_completos), 2) AS ent_completos_semana
FROM progreso
WHERE usuario_id = 1098337497
GROUP BY semana
ORDER BY semana;

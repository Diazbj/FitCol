SELECT 
    DATE_TRUNC('week', fecha) AS semana,
    ROUND(AVG(peso), 2) AS peso_semana,
    ROUND(AVG(imc), 2) AS imc_semana,
    ROUND(AVG(resistencia_cardio), 2) AS resistencia_semana
FROM progreso_usuarios
WHERE usuario_id = usuario_id
GROUP BY DATE_TRUNC('week', fecha)
ORDER BY semana;
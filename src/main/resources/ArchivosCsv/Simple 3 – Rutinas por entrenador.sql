SELECT DISTINCT r.*
FROM rutina r
JOIN rutina_plan_ent rpe ON r.cod_rutina = rpe.rutina_cod_rutina

JOIN plan_entrenamiento pe ON rpe.plan_entrenamiento_cod_plan_entrenamiento = pe.cod_plan_entrenamiento
WHERE pe.usuario_id = '1094935673';


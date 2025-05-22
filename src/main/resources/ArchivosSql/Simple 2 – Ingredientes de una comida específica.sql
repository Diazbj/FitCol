SELECT i.* 
FROM comida_ingrediente ci
JOIN ingrediente i ON ci.ingrediente_nombre = i.nombre
WHERE ci.comida_cod_comida = 12;

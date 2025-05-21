package co.edu.uniquindio.proyecto.dto.cliente;


import java.math.BigDecimal;
import java.sql.Date;

public record ProgresoSemanalDTO(
        Date semana,
        Double pesoSemana,
        Double imcSemana,
        BigDecimal entCompletosSemana

) {
}

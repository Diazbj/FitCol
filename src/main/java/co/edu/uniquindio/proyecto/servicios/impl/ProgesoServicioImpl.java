package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.progreso.PlanesDeficitDTO;
import co.edu.uniquindio.proyecto.dto.progreso.RankingClienteDTO;
import co.edu.uniquindio.proyecto.repositorio.Consultas.ProgresoRepo;
import co.edu.uniquindio.proyecto.servicios.ProgresoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgesoServicioImpl implements ProgresoServicio {

    private final ProgresoRepo progresoRepo;

    @Override
    public List<RankingClienteDTO>  obtenerRanking()throws Exception{
        List<Object[]> resultados = progresoRepo.obtenerTop5ClientesActivos();

        List<RankingClienteDTO> ranking = new ArrayList<>();

        for (Object[] fila : resultados) {
            String primerNombre = (String) fila[0];
            String segundoApellido = (String) fila[1];
            String clienteId = String.valueOf(fila[2]);
            Long totalEntCompletos = ((Number) fila[3]).longValue();

            ranking.add(new RankingClienteDTO(primerNombre, segundoApellido, clienteId, totalEntCompletos));
        }

        return ranking;
    }

    public List<PlanesDeficitDTO> obtenerPlanesDeficit() throws Exception{
        return progresoRepo.obtenerPlanesDeficitNutricionistas()
                .stream()
                .map(obj -> new PlanesDeficitDTO(
                        ((Number) obj[0]).longValue(),      // cod_plan_alimenticio
                        (String) obj[1],                    // nombre_plan
                        (Integer) obj[2],                   // duracion
                        (String) obj[3],                    // objetivo
                        (String) obj[4],                    // usuario_id
                        (String) obj[5],                    // nombre_nutricionista
                        (Integer) obj[6],                   // anios_exp
                        ((Number) obj[7]).intValue()        // calorias_totales_plan
                ))
                .toList();
    }


}

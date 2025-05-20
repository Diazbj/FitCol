package co.edu.uniquindio.proyecto.servicios.impl;

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


}

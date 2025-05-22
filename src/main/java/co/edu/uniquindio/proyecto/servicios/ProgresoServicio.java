package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.progreso.PlanesDeficitDTO;
import co.edu.uniquindio.proyecto.dto.progreso.RankingClienteDTO;


import java.util.List;

public interface ProgresoServicio {

    List<RankingClienteDTO> obtenerRanking() throws Exception;

   List<PlanesDeficitDTO> obtenerPlanesDeficit() throws Exception;
}

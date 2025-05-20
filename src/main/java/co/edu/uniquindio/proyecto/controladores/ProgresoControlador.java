package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.progreso.RankingClienteDTO;
import co.edu.uniquindio.proyecto.servicios.ProgresoServicio;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/progreso")
@RequiredArgsConstructor
public class ProgresoControlador {

    private final ProgresoServicio progresoServicio;

    @GetMapping("/ranking")
    @Operation(summary = "Top ranking clientes")
    public ResponseEntity<MensajeDTO<List<RankingClienteDTO>>> obtenerRanking()throws Exception{
        List<RankingClienteDTO> ranking= progresoServicio.obtenerRanking();
        return ResponseEntity.ok(new MensajeDTO<>(false,ranking));

    }
}

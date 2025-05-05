package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CiudadDTO;
import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.proyecto.repositorio.CiudadRepo;
import org.springframework.web.bind.annotation.RequestMapping;



import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@RequiredArgsConstructor
public class CiudadControlador {

    private final CiudadRepo ciudadRepo;

    @GetMapping
    public List<CiudadDTO> obtenerCiudades() {
        List<Ciudad> ciudades = ciudadRepo.findAll();
        return ciudades.stream()
                .map(ciudad -> new CiudadDTO(ciudad.getId(), ciudad.getNombre()))
                .toList();
    }
}


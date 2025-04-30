package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.servicios.EntrenadorServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Entrenador")
public class EntrenadorControlador {

    private final EntrenadorServicio entrenadorServicio;

    @PostMapping("/{id}")
    @Operation(summary = "Crear Plan de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> crearPlanEntrenamiento(@Valid @RequestBody CrearPlanEntrenamientoDTO crearPlanEntrenamientoDTO, @PathVariable String id) throws Exception{
        entrenadorServicio.crearPlanEntrenamiento(crearPlanEntrenamientoDTO,id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }



}

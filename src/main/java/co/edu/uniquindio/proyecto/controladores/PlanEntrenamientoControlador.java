package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.PlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.servicios.PlanEntrenamientoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planEntrenamiento")
@RequiredArgsConstructor
public class PlanEntrenamientoControlador {

    private final PlanEntrenamientoServicio planEntrenamientoServicio;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Crear Plan de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearPlanEntrenamientoDTO dto) throws Exception {
        planEntrenamientoServicio.crearPlanEntrenamiento(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Plan creado correctamente"));
    }
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Editar Plan de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> editar(@PathVariable Long id, @Valid @RequestBody CrearPlanEntrenamientoDTO dto) throws Exception {
        planEntrenamientoServicio.editarPlanEntrenamiento(id, dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Plan editado correctamente"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Plan por ID")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<PlanEntrenamientoDTO>> obtener(@PathVariable Long id) throws Exception {
        PlanEntrenamientoDTO planEntrenamiento = planEntrenamientoServicio.obtenerPlanEntrenamiento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, planEntrenamiento));
    }


    @GetMapping
    @Operation(summary = "Listar todos los planes")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<List<PlanEntrenamientoDTO>>> listar() throws Exception {
        List<PlanEntrenamientoDTO> planes=planEntrenamientoServicio.listarPlanesEntrenamiento();
        return ResponseEntity.ok(new MensajeDTO<>(false, planes));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Plan de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable Long id) throws Exception {
        planEntrenamientoServicio.eliminarPlanEntrenamiento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Plan eliminado correctamente"));
    }
}

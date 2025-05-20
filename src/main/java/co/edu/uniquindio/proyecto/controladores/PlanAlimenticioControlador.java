package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.planAlimenticio.CrearPlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.dto.planAlimenticio.PlanAlimenticioDTO;
import co.edu.uniquindio.proyecto.servicios.PlanAlimenticioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planAlimenticio")
@RequiredArgsConstructor
public class PlanAlimenticioControlador {

    private final PlanAlimenticioServicio planAlimenticioServicio;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @Operation(summary = "Crear Plan Alimenticio")
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearPlanAlimenticioDTO dto) throws Exception {
        planAlimenticioServicio.crearPlanAlimenticio(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Plan alimenticio creado correctamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    @Operation(summary = "Editar Plan Alimenticio")
    public ResponseEntity<MensajeDTO<String>> editar(@PathVariable Long id, @Valid @RequestBody CrearPlanAlimenticioDTO dto) throws Exception {
        planAlimenticioServicio.editarPlanAlimenticio(id, dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Plan alimenticio editado correctamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Plan Alimenticio por ID")
    public ResponseEntity<MensajeDTO<PlanAlimenticioDTO>> obtener(@PathVariable Long id) throws Exception {
        PlanAlimenticioDTO plan = planAlimenticioServicio.obtenerPlanAlimenticio(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, plan));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Listar todos los planes alimenticios")
    public ResponseEntity<MensajeDTO<List<PlanAlimenticioDTO>>> listar() {
        return ResponseEntity.ok(new MensajeDTO<>(false, planAlimenticioServicio.listarPlanesAlimenticios()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Plan Alimenticio")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable Long id) throws Exception {
        planAlimenticioServicio.eliminarPlanAlimenticio(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Plan alimenticio eliminado correctamente"));
    }
}

package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.AsignarRutinasDTO;
import co.edu.uniquindio.proyecto.dto.rutina.CrearRutinaDTO;
import co.edu.uniquindio.proyecto.dto.rutina.RutinaDTO;
import co.edu.uniquindio.proyecto.servicios.RutinaServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@RequiredArgsConstructor
public class RutinaControlador {

    private final RutinaServicio rutinaServicio;

    @PostMapping
    @Operation(summary = "Crear Rutina")
    public ResponseEntity<MensajeDTO<String>> crearRutina(@Valid @RequestBody CrearRutinaDTO dto) throws Exception {
        rutinaServicio.crearRutina(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Rutina creada exitosamente"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Rutina")
    public ResponseEntity<MensajeDTO<String>> editarRutina(@PathVariable Long id, @Valid @RequestBody CrearRutinaDTO dto) throws Exception {
        rutinaServicio.editarRutina(id, dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Rutina actualizada correctamente"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una Rutina por ID")
    public ResponseEntity<MensajeDTO<RutinaDTO>> obtenerRutina(@PathVariable Long id) throws Exception {
        RutinaDTO rutina = rutinaServicio.obtenerRutina(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, rutina));
    }

    @GetMapping("/entrenador/{id}")
    @Operation(summary = "Listar todas las Rutinas")
    public ResponseEntity<MensajeDTO<List<RutinaDTO>>> listarRutinas(@PathVariable Long id) throws Exception{
        List<RutinaDTO> lista = rutinaServicio.listarRutinas(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Rutina")
    public ResponseEntity<MensajeDTO<String>> eliminarRutina(@PathVariable Long id) throws Exception {
        rutinaServicio.eliminarRutina(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Rutina eliminada correctamente"));
    }

    @PostMapping("/asignarPlan")
    public ResponseEntity<String> asignarRutinasAPlan(@RequestBody AsignarRutinasDTO dto) throws Exception {
        rutinaServicio.asignarRutinasAPlan(dto);
        return ResponseEntity.ok("Rutinas asignadas correctamente al plan");
    }

}

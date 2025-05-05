package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearTipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.TipoEntrenamientoDTO;
import co.edu.uniquindio.proyecto.servicios.TipoEntrenamientoServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-entrenamiento")
@RequiredArgsConstructor
public class TipoEntrenamientoControlador {

    private final TipoEntrenamientoServicio servicio;

    @PostMapping
    @Operation(summary = "Crear Tipo de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> crear(@RequestBody @Valid CrearTipoEntrenamientoDTO dto) throws Exception {
        servicio.crearTipo(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Tipo creado correctamente"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Tipo de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> editar(@PathVariable Long id, @RequestBody @Valid CrearTipoEntrenamientoDTO dto) throws Exception {
        servicio.editarTipo(id, dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Tipo actualizado correctamente"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Tipo por ID")
    public ResponseEntity<MensajeDTO<TipoEntrenamientoDTO>> obtener(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new MensajeDTO<>(false, servicio.obtenerTipo(id)));
    }

    @GetMapping
    @Operation(summary = "Listar todos los Tipos")
    public ResponseEntity<MensajeDTO<List<TipoEntrenamientoDTO>>> listar() {
        return ResponseEntity.ok(new MensajeDTO<>(false, servicio.listarTipos()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Tipo de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable Long id) throws Exception {
        servicio.eliminarTipo(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Tipo eliminado correctamente"));
    }
}

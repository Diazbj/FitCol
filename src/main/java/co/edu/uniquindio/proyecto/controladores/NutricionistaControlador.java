package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.CrearNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.EditarNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.NutricionistaDTO;
import co.edu.uniquindio.proyecto.servicios.NutricionistaServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Nutricionista")
public class NutricionistaControlador {

    private final NutricionistaServicio nutricionistaServicio;

    @PostMapping
    @Operation(summary="Crear Nutricionista")
    public ResponseEntity<MensajeDTO<String>> crearNutricionista(@Valid @RequestBody CrearNutricionistaDTO crearNutricionista)throws Exception{
        nutricionistaServicio.crearNutricionista(crearNutricionista);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }


    @GetMapping("/{id}")
    @Operation(summary="Consultar Nutricionista")
    public ResponseEntity<MensajeDTO<NutricionistaDTO>> obtenerNutricionista(@PathVariable String id)throws Exception{
        NutricionistaDTO info=nutricionistaServicio.obtenerNutricionista(id);
        return ResponseEntity.ok(new MensajeDTO<>(true, info));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Nutricionista")
    public ResponseEntity<MensajeDTO<String>> eliminarNutricionista(@PathVariable String id)throws Exception{
        nutricionistaServicio.eliminarNutricionista(id);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Nutricionista eliminado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Nutricionista")
    public ResponseEntity<MensajeDTO<String>> editarNutricionista(@PathVariable String id, @Valid @RequestBody EditarNutricionistaDTO editarNutricionista)throws Exception {
        nutricionistaServicio.editarNutricionista(id,editarNutricionista);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Nutricionista editado"));
    }
}

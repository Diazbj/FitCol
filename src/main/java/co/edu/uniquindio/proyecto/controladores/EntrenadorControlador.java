package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.*;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.servicios.EntrenadorServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entrenadores")
public class EntrenadorControlador {

    private final EntrenadorServicio entrenadorServicio;

    @PostMapping
    @Operation(summary="Crear Entrenador")
    public ResponseEntity<MensajeDTO<String>> crearEntrenador(@Valid @RequestBody CrearEntrenadorDTO crearEntrenador)throws Exception{
        entrenadorServicio.crearEntrenador(crearEntrenador);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }


    @GetMapping()
    @Operation(summary="Consultar Entrenador")
    public ResponseEntity<MensajeDTO<EntrenadorDTO>> obtenerEntrenador()throws Exception{
        EntrenadorDTO info=entrenadorServicio.obtenerEntrenador();
        return ResponseEntity.ok(new MensajeDTO<>(true, info));
    }

    @DeleteMapping()
    @Operation(summary = "Eliminar Entrenador")
    public ResponseEntity<MensajeDTO<String>> eliminarEntrenador()throws Exception{
        entrenadorServicio.eliminarEntrenador();
        return ResponseEntity.ok(new MensajeDTO<>(true, "Entrenador eliminado"));
    }

    @PutMapping()
    @Operation(summary = "Editar Entrenador")
    public ResponseEntity<MensajeDTO<String>> editarEntrenador(@Valid @RequestBody EditarEntrenadorDTO editarEntrenador)throws Exception {
        entrenadorServicio.editarEntrenador(editarEntrenador);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Entrenador editado"));
    }

    @PostMapping("/plan/{id}")
    @Operation(summary = "Crear Plan de Entrenamiento")
    public ResponseEntity<MensajeDTO<String>> crearPlanEntrenamiento(@Valid @RequestBody CrearPlanEntrenamientoDTO crearPlanEntrenamientoDTO, @PathVariable String id) throws Exception{
        entrenadorServicio.crearPlanEntrenamiento(crearPlanEntrenamientoDTO,id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }

    @PostMapping("/certificados/{id}")
    @Operation(summary="Subir Certificados")
    public ResponseEntity<MensajeDTO<String>> subirCertificado(@Valid @RequestBody CertificacionDTO certificacionDTO, @PathVariable String id)throws Exception{
        entrenadorServicio.subirCertificado(certificacionDTO,id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su certificado se ha registrado con exito"));
    }


}

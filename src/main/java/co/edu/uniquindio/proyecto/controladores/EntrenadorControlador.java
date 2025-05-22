package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.entrenador.*;
import co.edu.uniquindio.proyecto.dto.planEntrenamiento.CrearPlanEntrenamientoDTO;
import co.edu.uniquindio.proyecto.servicios.EntrenadorServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    @Operation(summary="Consultar Entrenador")
    public ResponseEntity<MensajeDTO<EntrenadorDTO>> obtenerEntrenador()throws Exception{
        EntrenadorDTO info=entrenadorServicio.obtenerEntrenador();
        return ResponseEntity.ok(new MensajeDTO<>(true, info));
    }
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping()
    @Operation(summary = "Eliminar Entrenador")
    public ResponseEntity<MensajeDTO<String>> eliminarEntrenador()throws Exception{
        entrenadorServicio.eliminarEntrenador();
        return ResponseEntity.ok(new MensajeDTO<>(true, "Entrenador eliminado"));
    }
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping()
    @Operation(summary = "Editar Entrenador")
    public ResponseEntity<MensajeDTO<String>> editarEntrenador(@Valid @RequestBody EditarEntrenadorDTO editarEntrenador)throws Exception {
        entrenadorServicio.editarEntrenador(editarEntrenador);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Entrenador editado"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/certificados/{id}")
    @Operation(summary="Subir Certificados")
    public ResponseEntity<MensajeDTO<String>> subirCertificado(@Valid @RequestBody CertificacionDTO certificacionDTO)throws Exception{
        entrenadorServicio.subirCertificado(certificacionDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su certificado se ha registrado con exito"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/destacados")
    @Operation(summary="Obtener entrenadores destacados")
    public ResponseEntity<MensajeDTO<List<EntrenadoresDestacadoDTO>>> obtenerEntrenadoresDestacados()throws Exception{
        List<EntrenadoresDestacadoDTO> entrenadoresDestacadoDTOS=entrenadorServicio.obtenerEntrenadoresDestacados();
        return ResponseEntity.ok(new MensajeDTO<>(true, entrenadoresDestacadoDTOS));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/informacion")
    @Operation(summary="Obtener informacion de entrenador")
    public ResponseEntity<MensajeDTO<List<CertificadoEntrenadorDTO>>> obtenerInformacionEntrenador()throws Exception{
        List<CertificadoEntrenadorDTO> certificadoEntrenadorDTOS=entrenadorServicio.obtenerInformacionEntrenador();
        return ResponseEntity.ok(new MensajeDTO<>(true, certificadoEntrenadorDTOS));
    }


}

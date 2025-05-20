package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.CrearIngredienteDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;
import co.edu.uniquindio.proyecto.servicios.IngredienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingrediente")
@SecurityRequirement(name = "bearerAuth")
public class IngredienteControlador {

    private final IngredienteServicio ingredienteServicio;

    @PostMapping
    @Operation(summary = "Crear ingrediente")
    public ResponseEntity<MensajeDTO<String>> crearIngrediente(@Valid @RequestBody CrearIngredienteDTO crearIngredienteDTO) throws Exception {
        ingredienteServicio.crearIngrediente(crearIngredienteDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Ingrediente creada correctamente"));
    }

    @GetMapping("{nombre}")
    @Operation(summary = "Obtener Ingrediente")
    public ResponseEntity<MensajeDTO<IngredienteDTO>> obtenerIngrediente(@PathVariable String nombre ) throws Exception {

        IngredienteDTO ingrediente = ingredienteServicio.obtenerIngrediente(nombre);
        return ResponseEntity.ok(new MensajeDTO<>(true, ingrediente));

    }

    @GetMapping
    @Operation(summary = "obtener todos los ingredientes")
    public ResponseEntity<MensajeDTO<List<IngredienteDTO>>> obtenerIngredientes() throws Exception {
        List<IngredienteDTO> ingredientes = ingredienteServicio.obtenerIngredientes();
        return ResponseEntity.ok(new MensajeDTO<>(false, ingredientes));
    }

    @DeleteMapping("{nombre}")
    @Operation(summary = "Eliminar un ingrediente")
    public ResponseEntity<MensajeDTO<String>> eliminarIngrediente(@PathVariable String nombre) throws Exception {
        ingredienteServicio.eliminarIngrediente(nombre);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Ingrediente eliminado"));
    }

    @GetMapping("/comida/{id}")
    @Operation(summary = "Obtener ingredientes por comida")
    public ResponseEntity<MensajeDTO<List<IngredienteDTO>>> obtenerIngredientesPorComida(@PathVariable Long id) throws Exception {
     List<IngredienteDTO> ingredientes=ingredienteServicio.obtenerIngredientesPorComidas(id);
     return ResponseEntity.ok(new MensajeDTO<>(false, ingredientes));
    }

    @PostMapping("/asignarIngrdiente")
    @Operation(summary = "Asignar Ingrediente a comida")
    public ResponseEntity<MensajeDTO<IngredienteComidaDTO>> asignarIngredienteAComida(@Valid @RequestBody IngredienteComidaDTO ingredienteComidaDTO) throws Exception{
        IngredienteComidaDTO resultado = ingredienteServicio.asignarIngredienteAComida(ingredienteComidaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(true,resultado));
    }

}

package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comida.AsignarComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.CrearComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;
import co.edu.uniquindio.proyecto.servicios.ComidaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comida")
@SecurityRequirement(name = "bearerAuth")
public class ComidaControlador {

    private final ComidaServicio comidaServicio;

    @PostMapping
    @Operation(summary = "Crear comida")
    public ResponseEntity<MensajeDTO<String>> crearComida(@Valid @RequestBody CrearComidaDTO crearComidaDTO) throws Exception{
        comidaServicio.crearComida(crearComidaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Comida creada exitosamente"));
    }

    @GetMapping
    @Operation(summary = "Obtener Comidas")
    public ResponseEntity<MensajeDTO<List<ComidaDTO>>> obtenerComidas() throws Exception{
        List<ComidaDTO> comidas=comidaServicio.obtenerComidas();
        return ResponseEntity.ok(new MensajeDTO<>(true,comidas));

    }

    @PutMapping("{id}")
    @Operation(summary = "Editar comida")
    public ResponseEntity<MensajeDTO<String>> editarComida(@PathVariable Long id,@Valid @RequestBody CrearComidaDTO crearComidaDTO) throws Exception{
        comidaServicio.editarComida(id,crearComidaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(true,"Comida editada"));

    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar una comida")
    public ResponseEntity<MensajeDTO<String>> eliminarComida(@PathVariable Long id) throws Exception{
        comidaServicio.eliminarComida(id);
        return ResponseEntity.ok(new MensajeDTO<>(true,"Comida eliminada"));
    }



    @PostMapping("/asignarComidaAPlan")
    @Operation(summary = "Asignar comida a plan")
    public ResponseEntity<MensajeDTO<String>> asignarComidaAPlanAlimenticio (@RequestBody AsignarComidaDTO dto)throws Exception{
        comidaServicio.asignarComidaAPlanAlimenticio(dto);
        return ResponseEntity.ok(new MensajeDTO<>(true,"Comida asignada"));
    }

    @GetMapping("/plan/{id}")
    @Operation(summary = "listar Comidas por plan alimenticio")
    public ResponseEntity<MensajeDTO<List<ComidaDTO>>> listarComidasPorPlan(@PathVariable Long id) throws Exception{
        List<ComidaDTO> comidas =comidaServicio.listarComidasPorPlan(id);
        return ResponseEntity.ok(new MensajeDTO<>(true,comidas));
    }


}

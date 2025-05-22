package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.*;
import co.edu.uniquindio.proyecto.servicios.NutricionistaServicio;
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
@RequestMapping("/api/nutricionistas")
public class NutricionistaControlador {

    private final NutricionistaServicio nutricionistaServicio;

    @PostMapping
    @Operation(summary = "Crear Nutricionista")
    public ResponseEntity<MensajeDTO<String>> crearNutricionista(@Valid @RequestBody CrearNutricionistaDTO crearNutricionista) throws Exception {
        nutricionistaServicio.crearNutricionista(crearNutricionista);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    @Operation(summary = "Consultar Nutricionista")
    public ResponseEntity<MensajeDTO<NutricionistaDTO>> obtenerNutricionista() throws Exception {
        NutricionistaDTO info = nutricionistaServicio.obtenerNutricionista();
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping()
    @Operation(summary = "Eliminar Nutricionista")
    public ResponseEntity<MensajeDTO<String>> eliminarNutricionista() throws Exception {
        nutricionistaServicio.eliminarNutricionista();
        return ResponseEntity.ok(new MensajeDTO<>(false, "Nutricionista eliminado"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping()
    @Operation(summary = "Editar Nutricionista")
    public ResponseEntity<MensajeDTO<String>> editarNutricionista( @Valid @RequestBody EditarNutricionistaDTO editarNutricionista)throws Exception {
        nutricionistaServicio.editarNutricionista(editarNutricionista);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Nutricionista editado"));
    }

    @PostMapping("/Titulo/{id}")
    @Operation(summary="Subir Titulo Universitario")
    public ResponseEntity<MensajeDTO<String>> subirTitulo(@Valid @RequestBody TituloDTO tituloDTO, @PathVariable String id)throws Exception{
        nutricionistaServicio.subirTitulo(tituloDTO,id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su titulo se ha registrado con exito"));
    }

    @PutMapping("/titulo/{id}")
    @Operation(summary = "Editar titulo")
    public ResponseEntity<MensajeDTO<String>> editarTitulo(@PathVariable String id, @Valid @RequestBody TituloDTO tituloDTO)throws Exception {
        nutricionistaServicio.editarTitulo(tituloDTO,id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Titulo editado"));
    }

    @DeleteMapping("/titulo/{id}")
    @Operation(summary = "Eliminar titulo")
    public ResponseEntity<MensajeDTO<String>> eliminarTitulo(@PathVariable String id)throws Exception {
        nutricionistaServicio.eliminarTitulo(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Titulo eliminado"));
    }

    @GetMapping("/{id}/titulo")
    @Operation(summary = "Obtener un titulo de un nutricionista")
    public ResponseEntity<MensajeDTO<TituloDTO>> obtenerTitulo(@PathVariable String id)throws Exception {
        TituloDTO titulo=nutricionistaServicio.obtenerTitulo(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,titulo));
    }

    @GetMapping("/{id}/titulos")
    @Operation(summary = "Obtener todos los titulos de un nutricionista")
    public ResponseEntity<MensajeDTO<List<TituloDTO>>> obtenerTitulos(@PathVariable String id)throws Exception{
        List<TituloDTO> titulos= nutricionistaServicio.obtenerTitulos(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,titulos));
    }

    @GetMapping("/suscritos")
    @Operation(summary = "Clientes suscrito a planes")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MensajeDTO<List<ClienteSuscritoDTO>>> obtenerSuscritos() throws Exception{
        List<ClienteSuscritoDTO> clienteSuscritoDTOS=nutricionistaServicio.obtenerClienteSuscrito();
        return ResponseEntity.ok(new MensajeDTO<>(false,clienteSuscritoDTOS));

    }

}

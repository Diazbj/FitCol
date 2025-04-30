package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Usuario")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @PostMapping
    @Operation(summary="Crear Usuario")
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearUsuarioDTO crearUsuario)throws Exception{
        usuarioServicio.crearUsuario(crearUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }

    @GetMapping("/{id}")
    @Operation(summary="Consultar Usuario")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerUsuario(@PathVariable String id)throws Exception{
        UsuarioDTO info=usuarioServicio.obtenerUsuario(id);
        return ResponseEntity.ok(new MensajeDTO<>(true, info));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Usuario")
    public ResponseEntity<MensajeDTO<String>> eliminarUsuario(@PathVariable String id)throws Exception{
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Usuario eliminado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Usuario")
    public ResponseEntity<MensajeDTO<String>> editarUsuario(@PathVariable String id, @Valid @RequestBody EditarUsuarioDTO editarUsuario)throws Exception {
        usuarioServicio.editarUsuario(id,editarUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Usuario editado"));
    }

}

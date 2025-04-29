package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.cliente.ClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.CrearClienteDTO;
import co.edu.uniquindio.proyecto.dto.cliente.EditarClienteDTO;
import co.edu.uniquindio.proyecto.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    @PostMapping
    @Operation(summary="Crear Cliente")
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearClienteDTO crearCiente)throws Exception{
        clienteServicio.crearCliente(crearCiente);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }

    @GetMapping("/{id}")
    @Operation(summary="Consultar Cliente")
    public ResponseEntity<MensajeDTO<ClienteDTO>> obtenerCliente(@PathVariable String id)throws Exception{
        ClienteDTO info=clienteServicio.obtenerCliente(id);
        return ResponseEntity.ok(new MensajeDTO<>(true, info));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cliente")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable String id)throws Exception{
        clienteServicio.eliminarCliente(id);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Cliente eliminado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar Cliente")
    public ResponseEntity<MensajeDTO<String>> editarCliente(@PathVariable String id, @Valid @RequestBody EditarClienteDTO editarCliente)throws Exception {
        clienteServicio.editarCliente(id,editarCliente);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Cliente editado"));
    }

}

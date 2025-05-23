package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.CrearEjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioDTO;
import co.edu.uniquindio.proyecto.dto.ejercicio.EjercicioRutinaDTO;
import co.edu.uniquindio.proyecto.servicios.EjercicioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/ejercicio")
    @SecurityRequirement(name = "bearerAuth")
    public class EjercicioControlador {

        private final EjercicioServicio ejercicioServicio;

        @PostMapping
        @Operation(summary = "Crear Ejercicio")
        public ResponseEntity<MensajeDTO<String>> crearEjercicio(@Valid @RequestBody CrearEjercicioDTO dto) throws Exception {
            ejercicioServicio.crearEjercicio(dto);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Ejercicio creado exitosamente"));
        }

        @PostMapping("/asignarEjercicio")
        @Operation(summary = "Asignar Ejercicio a Rutina")
        public ResponseEntity<MensajeDTO<EjercicioRutinaDTO>> asignarEjercicio(@Valid @RequestBody EjercicioRutinaDTO dto) throws Exception {
            EjercicioRutinaDTO resultado = ejercicioServicio.asignarEjercicioRutina(dto);
            return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
        }

        @DeleteMapping("/eliminarAsignacion")
        public ResponseEntity<MensajeDTO<String>> eliminarAsignacion(
                @RequestParam Long idEjercicio,
                @RequestParam Long idRutina) {
            try {
                ejercicioServicio.eliminarAsignacionEjercicioRutina(idEjercicio, idRutina);
                return ResponseEntity.ok(new MensajeDTO<>(false, "Asignación eliminada correctamente"));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MensajeDTO<>(true, e.getMessage()));
            }
        }


        @PutMapping("/{id}")
        @Operation(summary = "Editar Ejercicio")
        public ResponseEntity<MensajeDTO<String>> editarEjercicio(@PathVariable Long id, @Valid @RequestBody CrearEjercicioDTO dto) throws Exception {
            ejercicioServicio.editarEjercicio(id, dto);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Ejercicio actualizado correctamente"));
        }

        @GetMapping("/{id}")
        @Operation(summary = "Obtener un Ejercicio por ID")
        public ResponseEntity<MensajeDTO<EjercicioDTO>> obtenerEjercicio(@PathVariable Long id) throws Exception {
            EjercicioDTO ejercicio = ejercicioServicio.obtenerEjercicio(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, ejercicio));
        }

        @GetMapping
        @Operation(summary = "Listar todos los Ejercicios")
        public ResponseEntity<MensajeDTO<List<EjercicioDTO>>> listarEjercicios() {
            List<EjercicioDTO> lista = ejercicioServicio.listarEjercicios();
            return ResponseEntity.ok(new MensajeDTO<>(false, lista));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar Ejercicio")
        public ResponseEntity<MensajeDTO<String>> eliminarEjercicio(@PathVariable Long id) throws Exception {
            ejercicioServicio.eliminarEjercicio(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Ejercicio eliminado correctamente"));
        }
    }

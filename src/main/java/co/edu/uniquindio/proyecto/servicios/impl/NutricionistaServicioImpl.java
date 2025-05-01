package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.nutricionista.CrearNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.EditarNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.NutricionistaDTO;
import co.edu.uniquindio.proyecto.mapper.NutricionistaMapper;
import co.edu.uniquindio.proyecto.mapper.PlanEntrenamientoMapper;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Nutricionista;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.NutricionistaRepo;
import co.edu.uniquindio.proyecto.repositorio.PlanEntrenamientoRepo;
import co.edu.uniquindio.proyecto.servicios.NutricionistaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class NutricionistaServicioImpl implements NutricionistaServicio {

    private final NutricionistaRepo nutricionistaRepo;
    private final NutricionistaMapper nutricionistaMapper;

    @Override
    public void crearNutricionista(CrearNutricionistaDTO crearNutricionistaDTO)throws Exception{
        // Validar si ya existe un nutricionista con ese ID
        if (nutricionistaRepo.existsById(crearNutricionistaDTO.id())) {
            throw new IllegalArgumentException("Ya existe un nutricionista con el ID: " + crearNutricionistaDTO.id());
        }

        Nutricionista nutricionista = nutricionistaMapper.fromCrearDTOToEntity(crearNutricionistaDTO);

        List<UsuarioTelefono> telefonos = crearNutricionistaDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono tel = new UsuarioTelefono();
                    tel.setNumero(numero);
                    tel.setUsuario(nutricionista);
                    return tel;
                }).toList();

        nutricionista.setTelefonos(telefonos);

        nutricionistaRepo.save(nutricionista);
    }

    @Override
    public NutricionistaDTO obtenerNutricionista(String id)throws Exception{
        // Buscar el nutricionista por ID
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));

        // Retornar el DTO con la edad calculada
        return nutricionistaMapper.fromEntityToDTO(nutricionista);
    }

    @Override
    public void eliminarNutricionista(String id)throws Exception{
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));
        nutricionistaRepo.deleteById(id);
    }
    @Override
    public void editarNutricionista(String id, EditarNutricionistaDTO editarNutricionistaDTO)throws Exception{
        // Buscar el nutricionista por ID
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));

        // Actualizar campos básicos con el mapper
        nutricionistaMapper.actualizarNutricionistaDesdeDTO(editarNutricionistaDTO, nutricionista);


        // Guardar los cambios
        nutricionistaRepo.save(nutricionista);
    }

}

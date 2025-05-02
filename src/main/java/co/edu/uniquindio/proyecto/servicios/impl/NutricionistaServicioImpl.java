package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.nutricionista.TituloDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.CrearNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.EditarNutricionistaDTO;
import co.edu.uniquindio.proyecto.dto.nutricionista.NutricionistaDTO;
import co.edu.uniquindio.proyecto.mapper.NutricionistaMapper;
import co.edu.uniquindio.proyecto.mapper.TituloMapper;
import co.edu.uniquindio.proyecto.modelo.entrenador.Entrenador;
import co.edu.uniquindio.proyecto.modelo.nutricionista.TituloUniversitario;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Nutricionista;
import co.edu.uniquindio.proyecto.modelo.nutricionista.NutricionistaTitulo;
import co.edu.uniquindio.proyecto.modelo.nutricionista.NutricionistaTituloId;
import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import co.edu.uniquindio.proyecto.modelo.vo.UsuarioTelefono;
import co.edu.uniquindio.proyecto.repositorio.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorio.NutricionistaRepo;
import co.edu.uniquindio.proyecto.repositorio.NutricionistaTituloRepo;
import co.edu.uniquindio.proyecto.repositorio.TituloRepo;
import co.edu.uniquindio.proyecto.servicios.NutricionistaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class NutricionistaServicioImpl implements NutricionistaServicio {

    private final NutricionistaRepo nutricionistaRepo;
    private final NutricionistaMapper nutricionistaMapper;
    private final TituloRepo tituloRepo;
    private final TituloMapper tituloMapper;
    private final NutricionistaTituloRepo nutricionistaTituloRepo;
    private final CiudadRepo ciudadRepo;

    @Override
    public void crearNutricionista(CrearNutricionistaDTO crearNutricionistaDTO)throws Exception{
        // Validar si ya existe un nutricionista con ese ID
        if (nutricionistaRepo.existsById(crearNutricionistaDTO.usuarioId())) {
            throw new IllegalArgumentException("Ya existe un nutricionista con el ID: " + crearNutricionistaDTO.usuarioId());
        }
        // Buscar la ciudad por ID
        Ciudad ciudad = ciudadRepo.findById(crearNutricionistaDTO.codCiudad())
                .orElseThrow(() -> new IllegalArgumentException("La ciudad con ID " + crearNutricionistaDTO.codCiudad() + " no existe"));

        Nutricionista nutricionista = nutricionistaMapper.fromCrearDTOToEntity(crearNutricionistaDTO);

        nutricionista.setCiudad(ciudad);

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

    @Override
    public void subirTitulo(TituloDTO tituloDTO, String id) throws Exception {
        // 1. Buscar el nutricionista por ID
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));

        // 2. Mapear y guardar la certificación
        TituloUniversitario titulo = tituloMapper.fromCrearDTOtoEntity(tituloDTO);
        titulo = tituloRepo.save(titulo); // <--- asegurar que tenga el ID

        if (titulo.getCodTitulo() == null) {
            throw new Exception("Error: No se generó un ID de certificación.");
        }

        // 3. Crear ID compuesto y relación
        NutricionistaTituloId relacionId = new NutricionistaTituloId(
                titulo.getCodTitulo(),
                nutricionista.getUsuarioId() // <-- verifica que este campo sea el mismo de tu PK
        );

        System.out.println("Certificación ID: " + titulo.getCodTitulo());
        System.out.println("Nutricionista ID: " + nutricionista.getUsuarioId());

        NutricionistaTitulo relacion = new NutricionistaTitulo(relacionId, nutricionista, titulo);

        // 4. Guardar la relación
        nutricionistaTituloRepo.save(relacion);
    }

}

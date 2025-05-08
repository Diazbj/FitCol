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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NutricionistaServicioImpl implements NutricionistaServicio {

    private final NutricionistaRepo nutricionistaRepo;
    private final NutricionistaMapper nutricionistaMapper;
    private final TituloRepo tituloRepo;
    private final TituloMapper tituloMapper;
    private final NutricionistaTituloRepo nutricionistaTituloRepo;
    private final CiudadRepo ciudadRepo;
    private final ClienteServicioImpl clienteServicioImpl;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void crearNutricionista(CrearNutricionistaDTO crearNutricionistaDTO)throws Exception{
        // Validar si ya existe un nutricionista con ese ID
        if (nutricionistaRepo.existsById(crearNutricionistaDTO.usuarioId())) {
            throw new IllegalArgumentException("Ya existe un nutricionista con el ID: " + crearNutricionistaDTO.usuarioId());
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
        nutricionista.setPassword(passwordEncoder.encode(crearNutricionistaDTO.password()));

        nutricionistaRepo.save(nutricionista);
    }

    @Override
    public NutricionistaDTO obtenerNutricionista()throws Exception{

        String id = clienteServicioImpl.obtenerIdSesion();

        // Buscar el nutricionista por ID
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));

        // Retornar el DTO con la edad calculada
        return nutricionistaMapper.fromEntityToDTO(nutricionista);
    }

    @Override
    public void eliminarNutricionista()throws Exception{

        String id = clienteServicioImpl.obtenerIdSesion();

        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));
        nutricionistaRepo.deleteById(id);
    }
    @Override
    public void editarNutricionista( EditarNutricionistaDTO editarNutricionistaDTO)throws Exception{

        String id = clienteServicioImpl.obtenerIdSesion();

        // Buscar el nutricionista por ID
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("El nutricionista con ID " + id + " no existe"));

        // Actualizar campos básicos con el mapper
        nutricionistaMapper.actualizarNutricionistaDesdeDTO(editarNutricionistaDTO, nutricionista);

        // Mapear los teléfonos manualmente (MapStruct no puede manejar relaciones complejas fácilmente)
        nutricionista.getTelefonos().clear();
        List<UsuarioTelefono> nuevosTelefonos = editarNutricionistaDTO.telefonos().stream()
                .map(numero -> {
                    UsuarioTelefono telefono = new UsuarioTelefono();
                    telefono.setNumero(numero);
                    telefono.setUsuario(nutricionista); // Relación correcta
                    return telefono;
                })
                .toList();
        nutricionista.getTelefonos().addAll(nuevosTelefonos);

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

    @Override
    public void editarTitulo(TituloDTO tituloDTO, String id) throws Exception {
        TituloUniversitario titulo = tituloRepo.findById(id)
                .orElseThrow(() -> new Exception("Título no encontrado"));

        titulo.setNombre(tituloDTO.nombre());
        titulo.setInstitucion(tituloDTO.institucion());

        tituloRepo.save(titulo);
    }

    @Override
    public void eliminarTitulo(String id) throws Exception {
        TituloUniversitario titulo = tituloRepo.findById(id)
                .orElseThrow(() -> new Exception("Título no encontrado"));

        // Opción: eliminar relaciones en la tabla intermedia si lo necesitas
        titulo.getNutricionistas().forEach(n -> n.getTitulos().remove(titulo));

        tituloRepo.delete(titulo);
    }

    @Override
    public TituloDTO obtenerTitulo(String id) throws Exception {
        TituloUniversitario titulo = tituloRepo.findById(id)
                .orElseThrow(() -> new Exception("Título no encontrado"));

        return tituloMapper.toDTO(titulo);
    }


    @Override
    public List<TituloDTO> obtenerTitulos(String id) throws Exception {
        Nutricionista nutricionista = nutricionistaRepo.findById(id)
                .orElseThrow(() -> new Exception("Nutricionista no encontrado"));

        List<TituloUniversitario> titulos = nutricionista.getTitulos();

        return titulos.stream()
                .map(tituloMapper::toDTO)
                .collect(Collectors.toList());
    }

}

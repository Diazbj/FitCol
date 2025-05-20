package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.CrearIngredienteDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;
import co.edu.uniquindio.proyecto.mapper.ComidaIngredienteMapper;
import co.edu.uniquindio.proyecto.mapper.IngredienteMapper;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Comida;
import co.edu.uniquindio.proyecto.modelo.nutricionista.ComidaIngrediente;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Ingrediente;
import co.edu.uniquindio.proyecto.repositorio.ComidaIngredienteRepo;
import co.edu.uniquindio.proyecto.repositorio.ComidaRepo;
import co.edu.uniquindio.proyecto.repositorio.Consultas.IngredienteRepo;
import co.edu.uniquindio.proyecto.servicios.IngredienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteServicioImpl implements IngredienteServicio {

    private final IngredienteMapper ingredienteMapper;
    private final IngredienteRepo ingredienteRepo;
    private final ComidaIngredienteRepo comidaIngredienteRepo;
    private final ComidaIngredienteMapper comidaIngredienteMapper;
    private final ComidaRepo comidaRepo;

    @Override
    public void crearIngrediente(CrearIngredienteDTO crearIngredienteDTO) {

        Ingrediente ingrediente = ingredienteMapper.fromCrearIngrediente(crearIngredienteDTO);
        ingredienteRepo.save(ingrediente);

    }

    @Override
    public IngredienteDTO obtenerIngrediente(String nombre)throws Exception{
       Ingrediente ingrediente=ingredienteRepo.findBynombre(nombre);

       if(ingrediente==null){
           throw new Exception("No existe el ingrediente con el nombre: "+nombre);
       }

       return ingredienteMapper.toDto(ingrediente);
    }

    @Override
    public List<IngredienteDTO> obtenerIngredientes() throws Exception {
        return ingredienteRepo
                .findAll()
                .stream()
                .map(ingredienteMapper::toDto)
                .toList();
    }

    @Override
    public void eliminarIngrediente(String nombre)throws Exception{
        Ingrediente ingrediente=ingredienteRepo.findBynombre(nombre);
        ingredienteRepo.delete(ingrediente);

    }

    @Override
    public List<IngredienteDTO> obtenerIngredientesPorComidas(Long codComida) throws Exception {
        List<Ingrediente> ingredientes = ingredienteRepo.buscarIngredientesPorCodComida(codComida);

        if (ingredientes.isEmpty()) {
            throw new Exception("No se encontraron ingredientes para la comida con ID: " + codComida);
        }

        return ingredientes
                .stream()
                .map(ingredienteMapper::toDto)
                .toList();
    }

    @Override
    public IngredienteComidaDTO asignarIngredienteAComida(IngredienteComidaDTO ingredienteComidaDTO) throws Exception {

        // 1. Buscar la comida
        Comida comida = comidaRepo.findById(ingredienteComidaDTO.codComida())
                .orElseThrow(() -> new Exception("Comida no encontrada con el código: " + ingredienteComidaDTO.codComida()));

        // 2. Buscar el ingrediente
        Ingrediente ingrediente = ingredienteRepo.findBynombre(ingredienteComidaDTO.ingrediente());
        if (ingrediente == null) {
            throw new Exception("Ingrediente no encontrado con el nombre: " + ingredienteComidaDTO.ingrediente());
        }

        // 3. Verificar si ya existe la relación (opcional, según tu lógica)
        if (comidaIngredienteRepo.findByComidaAndIngrediente(comida, ingrediente) != null) {
            throw new Exception("La comida ya tiene este ingrediente asignado");
        }

        // 4. Crear la relación
        ComidaIngrediente comidaIngrediente = new ComidaIngrediente();
        comidaIngrediente.setComida(comida);
        comidaIngrediente.setIngrediente(ingrediente);

        // 5. Guardar
        comidaIngredienteRepo.save(comidaIngrediente);

        // 6. Retornar DTO mapeado desde la entidad
        return comidaIngredienteMapper.toDTO(comidaIngrediente);
    }



}

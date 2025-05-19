package co.edu.uniquindio.proyecto.servicios.impl;


import co.edu.uniquindio.proyecto.dto.ingrediente.CrearIngredienteDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;
import co.edu.uniquindio.proyecto.mapper.IngredienteMapper;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Ingrediente;
import co.edu.uniquindio.proyecto.repositorio.IngredienteRepo;
import co.edu.uniquindio.proyecto.servicios.IngredienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredienteServicioImpl implements IngredienteServicio {

    private final IngredienteMapper ingredienteMapper;
    private final IngredienteRepo ingredienteRepo;

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

}

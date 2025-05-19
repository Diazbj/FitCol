package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.CrearComidaDTO;
import co.edu.uniquindio.proyecto.mapper.ComidaMapper;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Comida;
import co.edu.uniquindio.proyecto.repositorio.ComidaRepo;
import co.edu.uniquindio.proyecto.servicios.ComidaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComidaServicioImpl implements ComidaServicio {

    private final ComidaMapper comidaMapper;
    private final ComidaRepo comidaRepo;

    @Override
    public void crearComida(CrearComidaDTO crearComidaDTO) {

        Comida comida = comidaMapper.fromCrearComida(crearComidaDTO);
        comidaRepo.save(comida);

    }

    @Override
    public List<ComidaDTO> obtenerComidas() throws Exception{
        return comidaRepo
                .findAll()
                .stream()
                .map(comidaMapper::toDto)
                .toList();
    }

    @Override
    public void editarComida(Long id, CrearComidaDTO crearComidaDTO)throws Exception{

        Comida comida =comidaRepo.findById(id)
                .orElseThrow(() -> new Exception("No se encontró la comida con ID " + id));


        comida.setNombre(crearComidaDTO.nombre());
        comida.setCarbohidratos(crearComidaDTO.carbohidratos());
        comida.setGrasa( crearComidaDTO.grasa());
        comida.setPorcion( crearComidaDTO.porcion());
        comida.setProteinas(crearComidaDTO.proteinas());
        comidaRepo.save(comida);

    }

    @Override
    public void eliminarComida(Long id) throws Exception{
        comidaRepo.deleteById(id);
    }


}

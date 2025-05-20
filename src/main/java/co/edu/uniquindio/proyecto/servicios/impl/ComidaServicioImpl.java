package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.comida.AsignarComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.CrearComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;
import co.edu.uniquindio.proyecto.mapper.ComidaIngredienteMapper;
import co.edu.uniquindio.proyecto.mapper.ComidaMapper;
import co.edu.uniquindio.proyecto.mapper.PlanAlimenticioMapper;
import co.edu.uniquindio.proyecto.modelo.nutricionista.*;
import co.edu.uniquindio.proyecto.repositorio.*;
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
    private final IngredienteRepo ingredienteRepo;
    private final ComidaIngredienteRepo comidaIngredienteRepo;
    private final ComidaIngredienteMapper comidaIngredienteMapper;
    private final PlanAlimenticioRepo planAlimenticioRepo;
    private final ComidaPlanAliRepo comidaPlanAliRepo;


    @Override
    public void crearComida(CrearComidaDTO crearComidaDTO) {
        // 1. Obtener el plan alimenticio
        PlanAlimenticio planAlimenticio = planAlimenticioRepo.findById(crearComidaDTO.codPlanAlimenticio())
                .orElseThrow(() -> new RuntimeException("Plan alimenticio no encontrado"));

        // 2. Mapear y guardar la comida
        Comida comida = comidaMapper.fromCrearComida(crearComidaDTO);
        comidaRepo.save(comida);

        // 3. Crear la relación comida-planAlimenticio
        ComidaPlanAliId id = new ComidaPlanAliId(planAlimenticio.getCodPlanAlimenticio(), comida.getCodComida());
        ComidaPlanAli relacion = new ComidaPlanAli();
        relacion.setId(id);
        relacion.setPlanAlimenticio(planAlimenticio);
        relacion.setComida(comida);

        // 4. Asignar calorías
        relacion.setCaloriasDiarias(
                crearComidaDTO.proteinas() * 4 +
                        crearComidaDTO.carbohidratos() * 4 +
                        crearComidaDTO.grasa() * 9
        );

        comidaPlanAliRepo.save(relacion);
    }

    @Override
    public void asignarComidaAPlanAlimenticio(AsignarComidaDTO asignarComidaDTO) {
        // 1. Buscar comida
        Long codComida=asignarComidaDTO.codPlanAlimenticio();
        Comida comida = comidaRepo.findById(codComida)
                .orElseThrow(() -> new RuntimeException("Comida no encontrada con código: " + codComida));

        // 2. Buscar plan alimenticio
        Long codPlanAlimenticio=asignarComidaDTO.codPlanAlimenticio();
        PlanAlimenticio planAlimenticio = planAlimenticioRepo.findById(codPlanAlimenticio)
                .orElseThrow(() -> new RuntimeException("Plan alimenticio no encontrado con código: " + codPlanAlimenticio));

        // 3. Crear clave compuesta
        ComidaPlanAliId id = new ComidaPlanAliId(codPlanAlimenticio, codComida);

        // 4. Crear entidad relación
        ComidaPlanAli relacion = new ComidaPlanAli();
        relacion.setId(id);
        relacion.setComida(comida);
        relacion.setPlanAlimenticio(planAlimenticio);
        relacion.setCaloriasDiarias(asignarComidaDTO.caloriasDiarias()); // o asigna las calorías necesarias

        // 5. Guardar
        comidaPlanAliRepo.save(relacion);
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
    public void editarComida(Long id, CrearComidaDTO crearComidaDTO) throws Exception {
        Comida comida = comidaRepo.findById(id)
                .orElseThrow(() -> new Exception("No se encontró la comida con ID " + id));

        comida.setNombre(crearComidaDTO.nombre());
        comida.setCarbohidratos(crearComidaDTO.carbohidratos());
        comida.setGrasa(crearComidaDTO.grasa());
        comida.setPorcion(crearComidaDTO.porcion());
        comida.setProteinas(crearComidaDTO.proteinas());

        // 1. Limpiar ingredientes anteriores
        comida.getIngredientes().clear();

        comidaRepo.save(comida);
    }

    @Override
    public void eliminarComida(Long id) throws Exception{
        comidaRepo.deleteById(id);
    }



    @Override
    public List<ComidaDTO> listarComidasPorPlan(Long id)throws Exception{
        // 1. Verificar que el plan alimenticio exista
        PlanAlimenticio planAlimenticio = planAlimenticioRepo.findById(id)
                .orElseThrow(() -> new Exception("No se encontró el plan alimenticio con ID " + id));

        // 2. Obtener relaciones ComidaPlanAli por el plan
        List<ComidaPlanAli> relaciones = comidaPlanAliRepo.findAllByPlanAlimenticio_CodPlanAlimenticio(id);

        // 3. Mapear las comidas relacionadas a DTO
        return relaciones
                .stream()
                .map(rel -> comidaMapper.toDto(rel.getComida()))
                .toList();
    }



}



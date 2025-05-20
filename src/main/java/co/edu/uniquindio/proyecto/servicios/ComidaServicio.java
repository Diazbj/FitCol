package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.comida.AsignarComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.CrearComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;

import java.util.List;

public interface ComidaServicio {

    void crearComida(CrearComidaDTO crearComidaDTO) throws Exception;
    List<ComidaDTO> obtenerComidas()throws Exception;
    void editarComida(Long id,CrearComidaDTO crearComidaDTO) throws Exception;
    void eliminarComida(Long id) throws Exception;
    void asignarComidaAPlanAlimenticio(AsignarComidaDTO asignarComidaDTO) throws Exception;
    List<ComidaDTO> listarComidasPorPlan(Long id) throws Exception;

}

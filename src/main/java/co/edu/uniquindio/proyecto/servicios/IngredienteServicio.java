package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.comida.ComidaDTO;
import co.edu.uniquindio.proyecto.dto.comida.IngredienteComidaDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.CrearIngredienteDTO;
import co.edu.uniquindio.proyecto.dto.ingrediente.IngredienteDTO;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Ingrediente;

import java.sql.SQLException;
import java.util.List;

public interface IngredienteServicio {

    void crearIngrediente(CrearIngredienteDTO crearIngredienteDTO) throws Exception;
    IngredienteDTO obtenerIngrediente(String nombre) throws Exception;
    void eliminarIngrediente(String nombre) throws Exception;
    List<IngredienteDTO> obtenerIngredientes() throws Exception;
    List<IngredienteDTO> obtenerIngredientesPorComidas(Long id) throws Exception;
    IngredienteComidaDTO asignarIngredienteAComida(IngredienteComidaDTO ingredienteComidaDTO) throws Exception;

}

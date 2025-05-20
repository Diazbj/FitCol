package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.nutricionista.Comida;
import co.edu.uniquindio.proyecto.modelo.nutricionista.ComidaIngrediente;
import co.edu.uniquindio.proyecto.modelo.nutricionista.ComidaIngredienteId;
import co.edu.uniquindio.proyecto.modelo.nutricionista.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaIngredienteRepo extends JpaRepository<ComidaIngrediente, ComidaIngredienteId> {

    ComidaIngrediente findByComidaAndIngrediente(Comida comida, Ingrediente ingrediente);

}

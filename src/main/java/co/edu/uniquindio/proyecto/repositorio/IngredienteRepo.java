package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.nutricionista.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {
    @Query("SELECT ci.ingrediente FROM ComidaIngrediente ci WHERE ci.comida.codComida = :codComida")
    List<Ingrediente> buscarIngredientesPorCodComida(@Param("codComida") Long codComida);
    Ingrediente findBynombre(String nombre);
}

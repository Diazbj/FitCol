package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.nutricionista.NutricionistaTitulo;
import co.edu.uniquindio.proyecto.modelo.nutricionista.NutricionistaTituloId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutricionistaTituloRepo extends JpaRepository<NutricionistaTitulo, NutricionistaTituloId> {
}

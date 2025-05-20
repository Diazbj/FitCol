package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.nutricionista.ComidaPlanAli;
import co.edu.uniquindio.proyecto.modelo.nutricionista.ComidaPlanAliId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComidaPlanAliRepo extends JpaRepository<ComidaPlanAli, ComidaPlanAliId> {

    List<ComidaPlanAli> findAllByPlanAlimenticio_CodPlanAlimenticio(Long codPlanAlimenticio);

}

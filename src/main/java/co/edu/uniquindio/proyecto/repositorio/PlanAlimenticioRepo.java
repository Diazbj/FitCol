package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.nutricionista.PlanAlimenticio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanAlimenticioRepo extends JpaRepository<PlanAlimenticio, Long> {
}

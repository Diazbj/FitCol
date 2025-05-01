package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepo extends JpaRepository<Ciudad, String> {
}

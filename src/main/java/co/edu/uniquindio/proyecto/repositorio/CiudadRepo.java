package co.edu.uniquindio.proyecto.repositorio;


import co.edu.uniquindio.proyecto.modelo.vo.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRepo extends JpaRepository<Ciudad, String> {

    // Puedes agregar métodos personalizados si necesitas búsquedas específicas
    List<Ciudad> findByNombreContainingIgnoreCase(String nombre);

}

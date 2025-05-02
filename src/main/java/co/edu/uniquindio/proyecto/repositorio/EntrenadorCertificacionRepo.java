package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.entrenador.EntrenadorCertificacion;
import co.edu.uniquindio.proyecto.modelo.entrenador.EntrenadorCertificacionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorCertificacionRepo extends JpaRepository<EntrenadorCertificacion, EntrenadorCertificacionId> {
}

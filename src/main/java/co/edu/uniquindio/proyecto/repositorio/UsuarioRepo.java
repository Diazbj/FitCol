package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}

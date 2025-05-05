package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.entrenador.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepo extends JpaRepository<Ejercicio, Long> {}
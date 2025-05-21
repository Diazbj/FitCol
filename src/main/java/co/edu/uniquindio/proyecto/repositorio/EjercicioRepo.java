package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.entrenador.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EjercicioRepo extends JpaRepository<Ejercicio, Long> {


}
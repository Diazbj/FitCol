package co.edu.uniquindio.proyecto.modelo.entrenador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codRutina;

    private String nombre;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RutinaPlanEnt> rutinaPlanEnts;

    @OneToMany(mappedBy = "rutina")
    private List<EjercicioRutina> ejercicios;
}
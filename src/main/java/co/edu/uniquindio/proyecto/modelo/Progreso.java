package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Progreso {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Cliente cliente;   // Aquí se mapea la relación

    private Double peso;
    private LocalDate fechaRegistro;
    private Integer entCompletos;
    private Double indiceMC;
}

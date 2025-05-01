package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    @JoinColumn(name = "Cliente_Usuario_codigo")
    private Cliente cliente;

    private Double peso;
    private LocalDate fechaRegistro;
    private Integer entCompletos;
    private Double indiceMC;
}

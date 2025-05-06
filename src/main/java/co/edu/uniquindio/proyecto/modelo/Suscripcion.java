package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.modelo.entrenador.PlanEntrenamiento;
import co.edu.uniquindio.proyecto.modelo.nutricionista.PlanAlimenticio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SuscripcionId.class)
public class Suscripcion {

    private Integer duracion;
    private Double precio;
    private String nombre;

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    @Id
    @ManyToOne
    @JoinColumn(name = "codFactura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "codPlanEntrenamiento")
    private PlanEntrenamiento planEntrenamiento;

    @ManyToOne
    @JoinColumn(name = "codPlanAlimenticio")
    private PlanAlimenticio planAlimenticio;
}

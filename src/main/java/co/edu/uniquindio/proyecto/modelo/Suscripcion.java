package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codSuscripcion;

    private int duracion;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "Cliente_Usuario_codigo")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codPlanEntrenamiento")
    private PlanEntrenamiento planEntrenamiento;

    @ManyToOne
    @JoinColumn(name = "codPlanAlimenticio")
    private PlanAlimenticio planAlimentacion;

    @ManyToOne
    @JoinColumn(name = "Factura_codFactura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "codBeneficio")
    private Beneficio beneficio;
}

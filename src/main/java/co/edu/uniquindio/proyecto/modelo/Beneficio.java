package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codBeneficio;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false)
    private double descuento; // Porcentaje o valor fijo, según la lógica del negocio
}


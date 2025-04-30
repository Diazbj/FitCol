package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PlanAlimentacion")
public class PlanAlimenticio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codPlanAlimentacion")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    private Integer duracion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    //@ManyToOne
    //@JoinColumn(name = "Nutricionista_Cliente_Usuario_codigo")
    //private Nutricionista nutricionista;

    //@OneToMany(mappedBy = "planAlimentacion")
    //private List<ComidaPlanAlim> comidas;

    @OneToMany(mappedBy = "planAlimentacion")
    private List<Suscripcion> suscripciones;

}

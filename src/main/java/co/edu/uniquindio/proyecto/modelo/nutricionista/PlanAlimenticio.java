package co.edu.uniquindio.proyecto.modelo.nutricionista;

import co.edu.uniquindio.proyecto.modelo.Suscripcion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PlanAlimenticio")
public class PlanAlimenticio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPlanAlimenticio;

    @Column(columnDefinition = "TEXT")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    private Integer duracion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Nutricionista nutricionista;

    @OneToMany(mappedBy = "planAlimenticio",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ComidaPlanAli> comidas;

}

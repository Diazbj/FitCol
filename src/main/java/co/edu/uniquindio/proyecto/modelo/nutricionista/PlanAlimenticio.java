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
    private Integer codPlanAlimenticio;

    @Column(columnDefinition = "TEXT")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String objetivo;

    private Integer duracion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "Nutricionista_Cliente_Usuario_codigo")
    private Nutricionista nutricionista;

    @OneToMany(mappedBy = "planAlimentacion")
    private List<ComidaPlanAli> comidas;

}
